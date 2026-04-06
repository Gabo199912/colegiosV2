package com.educacion.inedcuchilla.servicio;


import com.educacion.inedcuchilla.SeguridadConfig.SecurityConfig;
import com.educacion.inedcuchilla.modelo.AlumnoModelo;
import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.repositorio.AlumnoRepositorio;
import com.educacion.inedcuchilla.repositorio.RolRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.*;

@Service
public class UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio;
    private final AlumnoRepositorio alumnoRepositorio;
    private final RolRepositorio rolRepositorio;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio, AlumnoRepositorio alumnoRepositorio, RolRepositorio rolRepositorio, PasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.alumnoRepositorio = alumnoRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioModelo> listarUsuarios(){
        return usuarioRepositorio.findAll();
    }

    public UsuarioModelo guardarUsuario(UsuarioModelo usuarioModelo){

        String nombreUsuario = usuarioModelo.getNombreUsuario();
        boolean existeEmail = existeUsuarioPorEmail(usuarioModelo.getEmail());
        String contrasenia = usuarioModelo.getContrasenia();

        if (existeUsuarioPorNombreUsuario(nombreUsuario)){
            throw new RuntimeException("El usuario ya existe");
        }

        if (existeEmail){
            throw new RuntimeException("El email ya existe");
        }

        if (contrasenia.length() < 7){
            throw new RuntimeException("La contraseña es muy corta");
        }

        usuarioModelo.setContrasenia(passwordEncoder.encode(contrasenia));
        return usuarioRepositorio.save(usuarioModelo);
    }

    public void eliminarUsuario(UsuarioModelo usuarioModelo){
    }

    public UsuarioModelo buscarUsuarioPorEmail(String email){
        return usuarioRepositorio.findByEmail(email);
    }

    public UsuarioModelo buscarPorNombreUsuario(String nombreUsuario){
        return usuarioRepositorio.findByNombreUsuario(nombreUsuario);
    }

    public boolean existeUsuarioPorNombreUsuario(String nombreUsuario){
        return usuarioRepositorio.existsByNombreUsuario(nombreUsuario);
    }

    public UsuarioModelo buscarPorIdUsuario(Integer idUsuario){
        return usuarioRepositorio.findByIdUsuario(idUsuario);
    }

    public boolean existeUsuarioPorNombre(String nombre){
        return usuarioRepositorio.existsByNombre(nombre);
    }

    public boolean existeUsuarioPorEmail(String email){
        return usuarioRepositorio.existsByEmail(email);
    }

    public Optional<UsuarioModelo> buscarUsuarioPorNombre(String nombre){
        return usuarioRepositorio.findByNombre(nombre);
    }

    public UsuarioModelo buscarUsuarioPorApellido(String apellido){
        return usuarioRepositorio.findByApellido(apellido);
    }



    @Transactional
    public void cargarExcel(@NotNull MultipartFile archivo) throws Exception{
        Workbook excelAlumnos = new XSSFWorkbook(archivo.getInputStream());
        List<UsuarioModelo> listaUsuarios = new ArrayList<>();
        RolModelo rol = rolRepositorio.findByTipoUsuario("ALUMNO");


        for (int i = 0; i < excelAlumnos.getNumberOfSheets(); i++) {
            Sheet hoja = excelAlumnos.getSheetAt(i);

            for (Row fila : hoja){
                try {
                    if (fila.getRowNum() == 0)continue;

                    UsuarioModelo usuarios = new UsuarioModelo();
                    AlumnoModelo alumnos = new AlumnoModelo();

                    usuarios.setNombreUsuario(fila.getCell(1).getStringCellValue());
                    usuarios.setNombre(fila.getCell(3).getStringCellValue());
                    usuarios.setApellido(fila.getCell(2).getStringCellValue());
                    usuarios.setEmail(fila.getCell(1).getStringCellValue()+ "@correo.com");
                    usuarios.setActivo(true);
                    usuarios.setFechaNacimiento(fila.getCell(4).getLocalDateTimeCellValue().toLocalDate());
                    usuarios.setRol(rol);

                    String contraseniaLimpiada = "" + fila.getCell(3).getStringCellValue().charAt(0) + fila.getCell(1).getStringCellValue().charAt(0);
                    contraseniaLimpiada = Normalizer.normalize(contraseniaLimpiada, Normalizer.Form.NFD);
                    contraseniaLimpiada = contraseniaLimpiada.replaceAll("[\\\\p{InCombiningDiacriticalMarks}]", "");
                    contraseniaLimpiada = contraseniaLimpiada.replaceAll("\\s+", "");

                    usuarios.setContrasenia(passwordEncoder.encode(contraseniaLimpiada.toLowerCase().trim()));

                    alumnos.setCodigoAlumno(fila.getCell(1).getStringCellValue());
                    alumnos.setGenero(String.valueOf(fila.getCell(6).getStringCellValue().charAt(0)));
                    alumnos.setSeccion(String.valueOf(fila.getCell(7).getStringCellValue().charAt(0)));
                    alumnos.setActivo(true);

                    alumnos.setUsuario(usuarios);
                    usuarios.setAlumno(alumnos);

                    listaUsuarios.add(usuarios);

                }catch (Exception e){
                    System.out.println("Error en la fila: " + fila.getRowNum() + " - " + e.getMessage());
                }
            }
        }


        usuarioRepositorio.saveAll(listaUsuarios);
        excelAlumnos.close();

    }
}

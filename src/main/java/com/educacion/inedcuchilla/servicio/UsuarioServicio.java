package com.educacion.inedcuchilla.servicio;


import com.educacion.inedcuchilla.DTO.UsuarioAlumnoDTO;
import com.educacion.inedcuchilla.modelo.AlumnoModelo;
import com.educacion.inedcuchilla.modelo.GradoModelo;
import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.repositorio.AlumnoRepositorio;
import com.educacion.inedcuchilla.repositorio.GradoRepositorio;
import com.educacion.inedcuchilla.repositorio.RolRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import jakarta.persistence.Cache;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.Normalizer;
import java.util.*;

@Service
public class UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio;
    private final AlumnoRepositorio alumnoRepositorio;
    private final RolRepositorio rolRepositorio;
    private final GradoRepositorio gradoRepositorio;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio,
                           AlumnoRepositorio alumnoRepositorio,
                           RolRepositorio rolRepositorio,
                           PasswordEncoder passwordEncoder,
                           GradoRepositorio gradoRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.alumnoRepositorio = alumnoRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.gradoRepositorio = gradoRepositorio;
    }

    public List<UsuarioModelo> listarUsuarios(){
        return usuarioRepositorio.findAll();
    }

    @Transactional
    public UsuarioModelo guardarUsuario(@NotNull UsuarioModelo usuarioModelo){

        String nombreUsuario = usuarioModelo.getNombreUsuario();
        boolean existeEmail = existeUsuarioPorEmail(usuarioModelo.getEmail());
        String contrasenia = usuarioModelo.getContrasenia();

        System.out.println(nombreUsuario + " " + "el servicio de usuarioServicio");

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

    @Transactional
    public void guardarUsuarioAlumno(@NotNull UsuarioAlumnoDTO usuarioAlumnoDTO){
            UsuarioModelo usuario = usuarioAlumnoDTO.getUsuario();
            AlumnoModelo alumno = usuarioAlumnoDTO.getAlumno();

        String nombreUsuario = usuario.getNombreUsuario();
        boolean existeEmail = existeUsuarioPorEmail(usuario.getEmail());
        String contrasenia = usuario.getContrasenia();

        if (existeUsuarioPorNombreUsuario(nombreUsuario)){
            throw new RuntimeException("El usuario ya existe");
        }

        if (existeEmail){
            throw new RuntimeException("El email ya existe");
        }

        if (contrasenia.length() < 7){
            throw new RuntimeException("La contraseña es muy corta");
        }

        usuario.setContrasenia(passwordEncoder.encode(contrasenia));

        alumno.setUsuario(usuario);
        usuario.setAlumno(alumno);

        usuarioRepositorio.save(usuario);
    }

    public Optional<UsuarioModelo> buscarPorNombreUsuario(String nombreUsuario){
        return usuarioRepositorio.findByNombreUsuario(nombreUsuario);
    }

    public boolean existeUsuarioPorNombreUsuario(String nombreUsuario){
        return usuarioRepositorio.existsByNombreUsuario(nombreUsuario);
    }

    public UsuarioModelo buscarPorIdUsuario(Integer idUsuario){
        return usuarioRepositorio.findByIdUsuario(idUsuario);
    }

    public boolean existeUsuarioPorEmail(String email){
        return usuarioRepositorio.existsByEmail(email);
    }

    public Optional<UsuarioModelo> buscarUsuarioPorNombre(String nombre){
        return usuarioRepositorio.findByNombre(nombre);
    }

    @Transactional
    public void cargarExcel(@NotNull MultipartFile archivo) throws Exception{
        Workbook excelAlumnos = new XSSFWorkbook(archivo.getInputStream());
        List<UsuarioModelo> listaUsuarios = new ArrayList<>();
        RolModelo rol = rolRepositorio.findByTipoUsuario("ALUMNO");


        for (int i = 0; i < excelAlumnos.getNumberOfSheets(); i++) {
            Sheet hoja = excelAlumnos.getSheetAt(i);

            String dato = hoja.getSheetName();
            GradoModelo gradoModelo;


            switch (dato){
                    case "4CA":
                        gradoModelo = gradoRepositorio.findById(1);
                        break;

                    case "4CB":
                        gradoModelo = gradoRepositorio.findById(2);
                        break;

                    case "4MA":
                        gradoModelo = gradoRepositorio.findById(3);
                        break;

                    case "4MB":
                        gradoModelo = gradoRepositorio.findById(4);
                        break;

                    case "5CA":
                        gradoModelo = gradoRepositorio.findById(5);
                        break;

                    case "5CB":
                        gradoModelo = gradoRepositorio.findById(6);
                        break;

                    case "5MA":
                        gradoModelo = gradoRepositorio.findById(7);
                        break;

                    case "5MB":
                        gradoModelo = gradoRepositorio.findById(8);
                        break;

                    default:
                        gradoModelo =  gradoRepositorio.findById(1);
            }


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

                    String contraseniaLimpiada = limpiarContrasenia("" + fila.getCell(3).getStringCellValue().charAt(0) + fila.getCell(1).getStringCellValue().charAt(0));


                    usuarios.setContrasenia(passwordEncoder.encode(contraseniaLimpiada));

                    alumnos.setCodigoAlumno(fila.getCell(1).getStringCellValue());
                    alumnos.setGenero(String.valueOf(fila.getCell(6).getStringCellValue().charAt(0)));
                    alumnos.setActivo(true);

                    alumnos.setGrado(gradoModelo);
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


    public static String limpiarContrasenia(String contraseniaLimpiada){

        contraseniaLimpiada = Normalizer.normalize(contraseniaLimpiada, Normalizer.Form.NFD);
        contraseniaLimpiada = contraseniaLimpiada.replaceAll("[\\\\p{InCombiningDiacriticalMarks}]", "");
        contraseniaLimpiada = contraseniaLimpiada.replaceAll("\\s+", "");
        contraseniaLimpiada.toLowerCase().trim();

        return contraseniaLimpiada;
    }
}

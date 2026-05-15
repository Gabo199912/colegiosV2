package com.educacion.inedcuchilla.servicio;


import com.educacion.inedcuchilla.DTO.UsuarioAlumnoDTO;
import com.educacion.inedcuchilla.DTO.UsuarioDTO;
import com.educacion.inedcuchilla.modelo.*;
import com.educacion.inedcuchilla.repositorio.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import java.text.Normalizer;
import java.util.*;

@Service
public class UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio;
    private final AlumnoRepositorio alumnoRepositorio;
    private final RolRepositorio rolRepositorio;
    private final GradoRepositorio gradoRepositorio;
    private final UsuarioRolRepositorio usuarioRolRepositorio;

    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private View error;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio,
                           AlumnoRepositorio alumnoRepositorio,
                           RolRepositorio rolRepositorio,
                           PasswordEncoder passwordEncoder,
                           GradoRepositorio gradoRepositorio,
                           UsuarioRolRepositorio usuarioRolRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.alumnoRepositorio = alumnoRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.gradoRepositorio = gradoRepositorio;
        this.usuarioRolRepositorio = usuarioRolRepositorio;
    }

    public List<UsuarioModelo> listarUsuarios(){
        return usuarioRepositorio.findAll();
    }



    @Transactional
    public UsuarioModelo guardarUsuario(@NotNull UsuarioDTO usuarioDTO){

        String nombreUsuario = usuarioDTO.getUsuario().getNombreUsuario();
        boolean existeEmail = existeUsuarioPorEmail(usuarioDTO.getUsuario().getEmail());
        String contrasenia = usuarioDTO.getUsuario().getContrasenia();

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

        usuarioDTO.getUsuario().setContrasenia(passwordEncoder.encode(contrasenia));

        UsuarioModelo usuarioGuardado = usuarioRepositorio.save(usuarioDTO.getUsuario());

        for (RolModelo rol: usuarioDTO.getRoles()){
            if (rolRepositorio.existsByIdRol(rol.getIdRol())){
                UsuarioRolModelo usuarioRol = new UsuarioRolModelo();

                usuarioRol.setRoles(rol);
                usuarioRol.setUsuario(usuarioGuardado);
                usuarioRolRepositorio.save(usuarioRol);
            }
        }


        return usuarioGuardado;
    }

    @Transactional
    public void guardarUsuarioAlumno(@NotNull UsuarioAlumnoDTO usuarioAlumnoDTO){
            UsuarioModelo usuario = usuarioAlumnoDTO.getUsuario();
            AlumnoModelo alumno = usuarioAlumnoDTO.getAlumno();
            RolModelo rol = rolRepositorio.findByTipoUsuario("ALUMNO");



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

        UsuarioRolModelo usuarioRolModelo = new UsuarioRolModelo();
        usuarioRolModelo.setUsuario(usuario);
        usuarioRolModelo.setRoles(rol);

        usuario.getUsuarioRol().add(usuarioRolModelo);

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

    public Map<String, Object> cargarExcel(@NotNull MultipartFile archivo) throws Exception{
        Workbook excelAlumnos = new XSSFWorkbook(archivo.getInputStream());

        List<UsuarioModelo> listaUsuarios = new ArrayList<>();
        List<String> errores = new ArrayList<>();

        int procesados = 0;
        int omitidos = 0;


        RolModelo rol = rolRepositorio.findByTipoUsuario("ALUMNO");

        Set<String> usuariosExistentes = new HashSet<>(usuarioRepositorio.cargarNombrsUsuario());
        Set<String> correosExistentes = new HashSet<>(usuarioRepositorio.cargarCorreo());


        for (int i = 0; i < excelAlumnos.getNumberOfSheets(); i++) {
            Sheet hoja = excelAlumnos.getSheetAt(i);

            String dato = hoja.getSheetName();

            GradoModelo gradoReal = verificarClase(dato);

            for (Row fila : hoja){

                try {
                    if (fila.getRowNum() == 0)continue;


                    UsuarioModelo usuarios = new UsuarioModelo();
                    AlumnoModelo alumnos = new AlumnoModelo();
                    UsuarioRolModelo usuarioRolModelo = new UsuarioRolModelo();

                    if (usuariosExistentes.contains(fila.getCell(1).getStringCellValue())){
                        errores.add("El usuario: " + fila.getCell(1).getStringCellValue() + " ya existe");
                        omitidos++;
                        continue;
                    }

                    if (correosExistentes.contains(fila.getCell(1).getStringCellValue()+ "@correo.com")){
                        errores.add("El correo: " + fila.getCell(1).getStringCellValue()+ "@correo.com" + " ya existe");
                        omitidos++;
                        continue;
                    }

                    usuarios.setNombreUsuario(fila.getCell(1).getStringCellValue());
                    usuarios.setNombre(fila.getCell(3).getStringCellValue());
                    usuarios.setApellido(fila.getCell(2).getStringCellValue());
                    usuarios.setEmail(fila.getCell(1).getStringCellValue()+ "@correo.com");
                    usuarios.setActivo(true);
                    usuarios.setFechaNacimiento(fila.getCell(4).getLocalDateTimeCellValue().toLocalDate());


                    String contraseniaLimpiada = limpiarContrasenia(fila.getCell(1).getStringCellValue());

                    usuarios.setContrasenia(passwordEncoder.encode(contraseniaLimpiada));

                    usuarioRolModelo.setUsuario(usuarios);
                    usuarioRolModelo.setRoles(rol);

                    usuarios.getUsuarioRol().add(usuarioRolModelo);

                    alumnos.setCodigoAlumno(fila.getCell(1).getStringCellValue());
                    alumnos.setGenero(String.valueOf(fila.getCell(6).getStringCellValue().charAt(0)));
                    alumnos.setActivo(true);

                    alumnos.setGrado(gradoReal);
                    alumnos.setUsuario(usuarios);
                    usuarios.setAlumno(alumnos);

                    listaUsuarios.add(usuarios);
                    procesados++;

                }catch (Exception e){
                    System.out.println("Error en la fila: " + fila.getRowNum() + " - " + e.getMessage());
                }
            }
        }


        usuarioRepositorio.saveAll(listaUsuarios);
        excelAlumnos.close();



        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Se cargaron " + procesados + " alumnos correctamente");
        respuesta.put("cargados correctamente", procesados);
        respuesta.put("omitidos", omitidos);
        respuesta.put("errores", errores);
        return respuesta;
    }

    public String limpiarContrasenia(String contraseniaLimpiada){

        contraseniaLimpiada = Normalizer.normalize(contraseniaLimpiada, Normalizer.Form.NFD);
        contraseniaLimpiada = contraseniaLimpiada.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        contraseniaLimpiada = contraseniaLimpiada.replaceAll("\\s+", "");
        contraseniaLimpiada = contraseniaLimpiada.toLowerCase().trim();

        return contraseniaLimpiada;
    }

    public GradoModelo verificarClase(@NonNull String dato){
        switch (dato){
            case "4CA":
                return gradoRepositorio.findById(5);

            case "4CB":
                return gradoRepositorio.findById(6);

            case "4MA":
                return gradoRepositorio.findById(7);

            case "4MB":
                return gradoRepositorio.findById(8);

            case "5CA":
                return gradoRepositorio.findById(2);

            case "5CB":
                return gradoRepositorio.findById(1);

            case "5MA":
                return gradoRepositorio.findById(3);

            case "5MB":
                return gradoRepositorio.findById(4);

            default:
                return   gradoRepositorio.findById(1);
        }
    }

}

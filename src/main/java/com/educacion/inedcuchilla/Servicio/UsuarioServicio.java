package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.AlumnoUsuarioDTO;
import com.educacion.inedcuchilla.DTO.UsuarioDTO;
import com.educacion.inedcuchilla.DTO.UsuarioRecordDTO;
import com.educacion.inedcuchilla.modelo.AlumnoModelo;
import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.modelo.UsuarioRolModelo;
import com.educacion.inedcuchilla.repositorio.AlumnoRepositorio;
import com.educacion.inedcuchilla.repositorio.RolRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRolRepositorio;
import org.apache.catalina.connector.Response;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final RolRepositorio rolRepositorio;
    private final UsuarioRolRepositorio usuarioRolRepositorio;
    private final AlumnoRepositorio alumnoRepositorio;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio,
                           PasswordEncoder passwordEncoder,
                           RolRepositorio rolRepositorio,
                           UsuarioRolRepositorio usuarioRolRepositorio,
                           AlumnoRepositorio alumnoRepositorio){
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.rolRepositorio = rolRepositorio;
        this.usuarioRolRepositorio = usuarioRolRepositorio;
        this.alumnoRepositorio = alumnoRepositorio;
    }



    @Transactional
    public Map<String, Object> guardarUsuario(UsuarioRecordDTO usuario){
        Map<String, Object> respuesta = new HashMap<>();
        Integer omitidos = 0;

        if (usuarioRepositorio.existsByNombreUsuario(usuario.nombreUsuario())){
            respuesta.put("MENSAJE", "el usuario ya existe.");
            return respuesta;
        }

        if (usuarioRepositorio.existsByEmail(usuario.email())){
            respuesta.put("MENSAJE", "el email ya existe, ingrese uno diferente.");
            return respuesta;
        }

        if (usuario.contrasenia().length() < 7){
            respuesta.put("MENSAJE", "la contraseña dbe tener minimo 7 caracteres");
            return respuesta;
        }

        UsuarioModelo usuarioNuevo = new UsuarioModelo();
        usuarioNuevo.setContrasenia(passwordEncoder.encode(usuario.contrasenia()));
        usuarioNuevo.setNombreUsuario(usuario.nombreUsuario());
        usuarioNuevo.setNombre(usuario.nombre());
        usuarioNuevo.setApellido(usuario.apellido());
        usuarioNuevo.setEmail(usuario.email());
        usuarioNuevo.setTelefono(usuario.telefono());
        usuarioNuevo.setFechaNacimiento(usuario.fechaNacimiento());
        usuarioNuevo.setActivo(true);

        UsuarioModelo usuarioGuardado = usuarioRepositorio.save(usuarioNuevo);

        /*funciona pero necesita mejora para que sea mas eficiente*/
        for (Integer rol : usuario.idRoles()){
            if (rolRepositorio.existsByIdRol(rol)){
                UsuarioRolModelo usuarioRolModelo = new UsuarioRolModelo();
                RolModelo rolModelo = rolRepositorio.findByIdRol(rol);
                usuarioRolModelo.setUsuario(usuarioGuardado);
                usuarioRolModelo.setRoles(rolModelo);
                usuarioRolRepositorio.save(usuarioRolModelo);
            }else {
                omitidos++;
            }
        }

        respuesta.put("MENSAJE", "Usuario creado correctamente.");
        respuesta.put("USUARIO", usuarioGuardado);
        respuesta.put("ROLES ", omitidos + " Roles omitidos.");
        return respuesta;
    }


    @Transactional
    public Map<String, Object> guardarSuperUsuario(@NonNull UsuarioDTO usuarioDTO){
        Map<String, Object> respuesta = new HashMap<>();

        String contrasenia = usuarioDTO.getUsuario().getContrasenia();

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

        respuesta.put("MENSAJE", "Usuario creado correctamente.");
        respuesta.put("USUARIO", usuarioGuardado);
        return respuesta;
    }

    public String desactivarUsuario(String nombreUsuario){
        Optional<UsuarioModelo> usuario = Optional.of(usuarioRepositorio.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new NoSuchElementException("El usario no esxiste.")));

        usuario.get().setActivo(false);
        usuarioRepositorio.save(usuario.get());

        return "Usuario correctamente desactivado.";
    }

    public boolean buscarPorNombreUsuario(String nombreUsuario){
        return usuarioRepositorio.existsByNombreUsuario(nombreUsuario);
    }

    @Transactional
    public ResponseEntity<?> guardarUsuarioConAlumno(AlumnoUsuarioDTO alumnoUsuario){
        Map<String, Object> respuesta = new HashMap<>();
        if (usuarioRepositorio.existsByNombreUsuario(alumnoUsuario.nombreUsuario())){
            respuesta.put("MENSAJE", "el usuario ingresado ya existe.");
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);

        }

        if (usuarioRepositorio.existsByEmail(alumnoUsuario.email())){
            respuesta.put("MENSAJE", "el email ingresado ya existe.");
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }

        UsuarioModelo usuarioNuevo = new UsuarioModelo();
        usuarioNuevo.setNombreUsuario(alumnoUsuario.nombreUsuario());
        usuarioNuevo.setNombre(alumnoUsuario.nombre());
        usuarioNuevo.setApellido(alumnoUsuario.apellido());
        usuarioNuevo.setEmail(alumnoUsuario.email());
        usuarioNuevo.setTelefono(alumnoUsuario.telefono());
        usuarioNuevo.setFechaNacimiento(alumnoUsuario.fechaNacimiento());
        usuarioNuevo.setActivo(true);
        usuarioNuevo.setContrasenia(passwordEncoder.encode(alumnoUsuario.contrasenia()));

        UsuarioModelo usuarioGuardado = usuarioRepositorio.save(usuarioNuevo);

        AlumnoModelo alumnoNuevo = new AlumnoModelo();

        alumnoNuevo.setCodigoAlumno(alumnoUsuario.codigoAlumno());
        alumnoNuevo.setGenero(alumnoUsuario.genero());
        alumnoNuevo.setUsuario(usuarioGuardado);
        alumnoNuevo.setActivo(true);

        alumnoRepositorio.save(alumnoNuevo);

        respuesta.put("MENSAJE", "el usuario se creo correctamente con el alumno");
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


    //continuar despues. 
//    public Map<String, Object> agregarRoles(List<RolModelo> roles, AsignarRoles usuarioConRoles) {
//        Set<Integer> idNuevos = new HashSet<>();
//        Optional<UsuarioModelo> usuario = usuarioRepositorio.findByNombreUsuario(usuarioConRoles.nombreUsuario());
//        List<UsuarioRolModelo> usuarioRol = new ArrayList<>();
//
//        for (RolModelo ur: roles){
//            idNuevos.add(ur.getIdRol());
//        }
//
//
//
//        usuarioRolRepositorio.saveAll(usuarioRol);
//
//        return
//    }


}

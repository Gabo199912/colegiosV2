package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Alumnos.AlumnoResponseDTO;
import com.educacion.inedcuchilla.DTO.Alumnos.AlumnoUsuarioRequestDTO;
import com.educacion.inedcuchilla.DTO.Alumnos.ConvertirAlumnoDTO;
import com.educacion.inedcuchilla.modelo.AlumnoModelo;
import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.modelo.UsuarioRolModelo;
import com.educacion.inedcuchilla.repositorio.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AlumnoServicio {
    private final UsuarioRepositorio usuarioRepositorio;
    private final AlumnoRepositorio alumnoRepositorio;
    private final InscripcionRepositorio inscripcionRepositorio;
    private final RolRepositorio rolRepositorio;
    private final UsuarioRolRepositorio usuarioRolRepositorio;

    public AlumnoServicio(UsuarioRepositorio usuarioRepositorio,
                          AlumnoRepositorio alumnoRepositorio,
                          InscripcionRepositorio inscripcionRepositorio,
                          RolRepositorio rolRepositorio,
                          UsuarioRolRepositorio usuarioRolRepositorio){
        this.usuarioRepositorio = usuarioRepositorio;
        this.alumnoRepositorio = alumnoRepositorio;
        this.inscripcionRepositorio = inscripcionRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.usuarioRolRepositorio = usuarioRolRepositorio;
    }


    @Transactional
    public ResponseEntity<Map<String, Object>> crearAlumnoUsuario(AlumnoUsuarioRequestDTO alumnoUsuario){
        Map<String, Object> respuesta = new HashMap<>();

        if (usuarioRepositorio.existsByNombreUsuario(alumnoUsuario.nombreUsuario())){
            respuesta.put("MENSAJE", "el usuario que intentas crear como alumno ya existe.");
            respuesta.put("COMO PROCEDER", "puedes utilizar la opcion de convertir usuario a un alumno o crear un alumno con otras credenciales." );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        AlumnoModelo alumnoNuevo = new AlumnoModelo();
        UsuarioModelo usuarioNuevo = new UsuarioModelo();
        RolModelo rol = rolRepositorio.findByIdRol(4);
        UsuarioRolModelo usuarioRol = new UsuarioRolModelo();

        usuarioNuevo.setNombreUsuario(alumnoUsuario.nombreUsuario());
        usuarioNuevo.setNombre(alumnoUsuario.nombre());
        usuarioNuevo.setApellido(alumnoUsuario.apellido());
        usuarioNuevo.setEmail(alumnoUsuario.email());
        usuarioNuevo.setTelefono(alumnoUsuario.telefono());
        usuarioNuevo.setActivo(true);
        usuarioNuevo.setFechaNacimiento(alumnoUsuario.fechaNacimiento());
        usuarioNuevo.setContrasenia(alumnoUsuario.contrasenia());

        UsuarioModelo usuarioGuardado = usuarioRepositorio.save(usuarioNuevo);

        alumnoNuevo.setCodigoAlumno(alumnoUsuario.codigoAlumno());
        alumnoNuevo.setGenero(alumnoUsuario.genero());
        alumnoNuevo.setActivo(true);
        alumnoNuevo.setUsuario(usuarioGuardado);


        AlumnoModelo alumnoGuardado = alumnoRepositorio.save(alumnoNuevo);
        usuarioRol.setRoles(rol);
        usuarioRol.setUsuario(usuarioGuardado);
        usuarioRolRepositorio.save(usuarioRol);


        AlumnoResponseDTO alumnoResponse = new AlumnoResponseDTO(
                usuarioGuardado.getNombreUsuario(),
                usuarioGuardado.getNombre(),
                usuarioGuardado.getEmail(),
                usuarioGuardado.getTelefono(),
                alumnoGuardado.getCodigoAlumno()
        );

        respuesta.put("MENSAJE", "El usuario se guardo correctamente.");
        respuesta.put("ALUMNO", alumnoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    public ResponseEntity<Map<String, Object>> convertirAlumno(ConvertirAlumnoDTO usuarioAlumno){
        Map<String, Object> respuesta = new HashMap<>();
        Optional<UsuarioModelo> usuario = usuarioRepositorio.findByNombreUsuario(usuarioAlumno.nombreUsuario());

        if (usuario.isEmpty()){
            respuesta.put("MENSAJE", "El usuario ingresado no existe.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        UsuarioRolModelo usuarioRol = new UsuarioRolModelo();
        RolModelo rol = rolRepositorio.findByIdRol(4);

        usuarioRol.setUsuario(usuario.get());
        usuarioRol.setRoles(rol);

        usuarioRolRepositorio.save(usuarioRol);

        AlumnoModelo alumno = new AlumnoModelo();

        alumno.setActivo(true);
        alumno.setGenero(usuarioAlumno.genero());
        alumno.setCodigoAlumno(usuarioAlumno.codigoAlumno());
        alumno.setUsuario(usuario.get());
        AlumnoModelo alumnoGuardado = alumnoRepositorio.save(alumno);

        AlumnoResponseDTO alumnoResponse = new AlumnoResponseDTO(
                usuario.get().getNombreUsuario(),
                usuario.get().getNombre(),
                usuario.get().getEmail(),
                usuario.get().getTelefono(),
                alumnoGuardado.getCodigoAlumno()
        );

        respuesta.put("MENSAJE", "El usuario se guardo correctamente como alumno.");
        respuesta.put("ALUMNO", alumnoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


}

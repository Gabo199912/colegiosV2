package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Alumnos.AlumnoUsuarioDTO;
import com.educacion.inedcuchilla.repositorio.AlumnoRepositorio;
import com.educacion.inedcuchilla.repositorio.InscripcionRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AlumnoServicio {
    private final UsuarioRepositorio usuarioRepositorio;
    private final AlumnoRepositorio alumnoRepositorio;
    private final InscripcionRepositorio inscripcionRepositorio;

    public AlumnoServicio(UsuarioRepositorio usuarioRepositorio,
                          AlumnoRepositorio alumnoRepositorio,
                          InscripcionRepositorio inscripcionRepositorio){
        this.usuarioRepositorio = usuarioRepositorio;
        this.alumnoRepositorio = alumnoRepositorio;
        this.inscripcionRepositorio = inscripcionRepositorio;
    }


    public ResponseEntity<Map<String, Object>> crearAlumnoUsuario(AlumnoUsuarioDTO alumnoUsuario){
        Map<String, Object> respuesta = new HashMap<>();



        if (!usuarioRepositorio.existsByNombreUsuario(alumnoUsuario.nombreUsuario())){
            respuesta.put("MENSAJE", "el usuario que intentas crear como alumno ya existe.");
            respuesta.put("COMO PROCEDER", "puedes utilizar la opcion de convertur usuario a un alumno o crear un alumno con otras credenciales." );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }




        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}

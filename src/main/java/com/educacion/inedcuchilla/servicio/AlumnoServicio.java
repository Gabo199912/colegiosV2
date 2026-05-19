package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.DTO.AlumnoDTO;
import com.educacion.inedcuchilla.modelo.AlumnoModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.repositorio.AlumnoRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlumnoServicio {
    private final AlumnoRepositorio alumnoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public AlumnoServicio (AlumnoRepositorio alumnoRepositorio, UsuarioRepositorio usuarioRepositorio){
        this.alumnoRepositorio = alumnoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }


    public Map<String, Object> buscarPorCodigo(String codigoAlumno){
        if (codigoAlumno.isEmpty()){
            Map<String, Object> respuesta = Map.of("mensaje", "El codigo del alumno no puede estar vacio");
            return respuesta;
        }

        AlumnoDTO alumno = alumnoRepositorio.buscarAlumnoPorCodigo(codigoAlumno);

        Map<String, Object> respuesta = Map.of("alumno", alumno);
        return respuesta;
    }

    public Map<String, Object> listarAlumnosCompleto(){
        List<AlumnoDTO> alumnos = new ArrayList<>();
        alumnos = alumnoRepositorio.listarAlumnos();

        if (alumnos.isEmpty()){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "SIN ALUMNOS ENCONTRADOS");
            respuesta.put("STATUS", HttpStatus.NOT_FOUND);
            return respuesta;
        }

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("ALUMNOS", alumnos);

        return respuesta;

    }

}

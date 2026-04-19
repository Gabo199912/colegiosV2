package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.DTO.AlumnoDTO;
import com.educacion.inedcuchilla.modelo.AlumnoModelo;
import com.educacion.inedcuchilla.repositorio.AlumnoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AlumnoServicio {
    private final AlumnoRepositorio alumnoRepositorio;

    public AlumnoServicio (AlumnoRepositorio alumnoRepositorio){
        this.alumnoRepositorio = alumnoRepositorio;
    }

    public List<AlumnoModelo> listarAlumnos(){
        return alumnoRepositorio.findAll();
    }

    public Map<String, Object> buscarPorCodigo(String codigoAlumno){
        if (codigoAlumno.isEmpty()){
            Map<String, Object> respuesta = Map.of("mensaje", "El nombre del alumno no puede estar vacio");
            return respuesta;
        }

        AlumnoDTO alumno = alumnoRepositorio.buscarAlumnoPorCodigo(codigoAlumno);

        Map<String, Object> respuesta = Map.of("alumno", alumno);
        return respuesta;
    }
}

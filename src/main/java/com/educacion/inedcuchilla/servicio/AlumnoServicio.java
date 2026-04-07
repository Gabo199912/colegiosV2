package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.modelo.AlumnoModelo;
import com.educacion.inedcuchilla.repositorio.AlumnoRepositorio;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServicio {
    private final AlumnoRepositorio alumnoRepositorio;

    public AlumnoServicio (AlumnoRepositorio alumnoRepositorio){
        this.alumnoRepositorio = alumnoRepositorio;
    }

}

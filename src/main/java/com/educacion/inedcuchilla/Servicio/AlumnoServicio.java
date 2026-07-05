package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.repositorio.AlumnoRepositorio;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServicio {
    private final AlumnoRepositorio alumnoRepositorio;

    public AlumnoServicio(AlumnoRepositorio alumnoRepositorio){
        this.alumnoRepositorio = alumnoRepositorio;
    }


}

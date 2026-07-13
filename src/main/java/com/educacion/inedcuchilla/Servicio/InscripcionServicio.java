package com.educacion.inedcuchilla.Servicio;


import com.educacion.inedcuchilla.repositorio.*;
import org.springframework.stereotype.Service;

@Service
public class InscripcionServicio {
    private final GradoRepositorio gradoRepositorio;
    private final EspecialidadRepositorio especialidadRepositorio;
    private final SeccionRepositorio seccionRepositorio;
    private final GradoAcademicoRepositorio gradoAcademicoRepositorio;
    private final AlumnoRepositorio alumnoRepositorio;


    public InscripcionServicio(GradoRepositorio gradoRepositorio,
                               EspecialidadRepositorio especialidadRepositorio,
                               SeccionRepositorio seccionRepositorio,
                               GradoAcademicoRepositorio gradoAcademicoRepositorio,
                               AlumnoRepositorio alumnoRepositorio){
        this.gradoRepositorio = gradoRepositorio;
        this.especialidadRepositorio = especialidadRepositorio;
        this.seccionRepositorio = seccionRepositorio;
        this.gradoAcademicoRepositorio = gradoAcademicoRepositorio;
        this.alumnoRepositorio = alumnoRepositorio;
    }
}

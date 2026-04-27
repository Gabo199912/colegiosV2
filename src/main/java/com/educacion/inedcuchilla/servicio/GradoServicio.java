package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.modelo.GradoModelo;
import com.educacion.inedcuchilla.repositorio.GradoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradoServicio {
    private final GradoRepositorio gradoRepositorio;

    public GradoServicio(GradoRepositorio gradoRepositorio) {
        this.gradoRepositorio = gradoRepositorio;
    }

    public GradoModelo buscarPorId(Integer id) {
        return gradoRepositorio.findById(id).orElse(null);
    }

    public List<GradoModelo> listarGrados(){
        return gradoRepositorio.findAll();
    }

}

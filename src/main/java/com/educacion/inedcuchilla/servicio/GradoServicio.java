package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.modelo.GradoModelo;
import com.educacion.inedcuchilla.repositorio.GradoRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> guardarGrado(GradoModelo gradoModelo){
        Map<String, Object> respuesta = new HashMap<>();

        char seccion = gradoModelo.getSeccion();

        if (gradoModelo.getNombreGrado().isEmpty()){
            respuesta.put("mensaje", "El nombre del grado no puede estar vacio");
            return respuesta;
        }

        if (gradoModelo.getEspecialidad().isEmpty()){
            respuesta.put("mensaje", "La especialidad del grado no puede estar vacia");
            return respuesta;
        }



        gradoRepositorio.save(gradoModelo);

        respuesta.put("RESPUESTA", "se guardo el grado correctactamente");

        return respuesta;
    }



}

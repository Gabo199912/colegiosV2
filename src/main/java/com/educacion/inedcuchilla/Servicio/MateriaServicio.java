package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Materias.MateriaRequest;
import com.educacion.inedcuchilla.repositorio.MateriaRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MateriaServicio {
    private final MateriaRepositorio materiaRepositorio;

    public MateriaServicio(MateriaRepositorio materiaRepositorio){
        this.materiaRepositorio = materiaRepositorio;
    }


    public ResponseEntity<Map<String, Object>> crearMateria(MateriaRequest materia){
        Map<String, Object> respuesta = new HashMap<>();


    }
}

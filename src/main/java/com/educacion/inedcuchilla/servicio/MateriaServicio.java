package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.modelo.MateriasModelo;
import com.educacion.inedcuchilla.repositorio.MateriaRepositorio;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MateriaServicio {
    private final MateriaRepositorio materiaRepositorio;

    public MateriaServicio(MateriaRepositorio materiaRepositorio){
        this.materiaRepositorio = materiaRepositorio;
    }

    public Map<String, Object> crearMateria(MateriasModelo materiasModelo){
        if (materiasModelo == null){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "todos los campos deben de estar completos");
            return respuesta;
        }

        materiaRepositorio.save(materiasModelo);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "Materia agregada correctamente");
        respuesta.put("MATERIA", materiasModelo);
        return respuesta;
    }

    public Map<String, Object> actualizarMateria(MateriasModelo materiasModelo){

        materiaRepositorio.save(materiasModelo);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "Materia agregada correctamente");
        respuesta.put("MATERIA", materiasModelo);
        return respuesta;
    }
}

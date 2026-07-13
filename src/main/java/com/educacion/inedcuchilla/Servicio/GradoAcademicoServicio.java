package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Grado.GradoDTO;
import com.educacion.inedcuchilla.modelo.GradoModelo;
import com.educacion.inedcuchilla.repositorio.EspecialidadRepositorio;
import com.educacion.inedcuchilla.repositorio.GradoRepositorio;
import com.educacion.inedcuchilla.repositorio.SeccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GradoAcademicoServicio {
    private final GradoRepositorio gradoRepositorio;
    private final EspecialidadRepositorio especialidadRepositorio;
    private final SeccionRepositorio seccionRepositorio;


    public GradoAcademicoServicio(GradoRepositorio gradoRepositorio,
                                  EspecialidadRepositorio especialidadRepositorio,
                                  SeccionRepositorio seccionRepositorio){
        this.gradoRepositorio = gradoRepositorio;
        this.especialidadRepositorio = especialidadRepositorio;
        this.seccionRepositorio = seccionRepositorio;
    }

    public ResponseEntity<Map<String, Object>> crearGrado(GradoDTO grado){
        Map<String, Object> respuesta = new HashMap<>();

        if (gradoRepositorio.findByGrado(grado.nombreGrado())){
            respuesta.put("MENSAJE", "El grado ingresado ya existe, intente crear uno nuevo.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        GradoModelo gradoNuevo = new GradoModelo();
        gradoNuevo.setGrado(grado.nombreGrado());

        GradoModelo gradoGuardado = gradoRepositorio.save(gradoNuevo);

        respuesta.put("MENSAJE", "grado guardado correctamente.");
        respuesta.put("GRADO", gradoGuardado);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

}

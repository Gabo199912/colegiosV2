package com.educacion.inedcuchilla.controlador;

import com.educacion.inedcuchilla.DTO.MateriasDTO;
import com.educacion.inedcuchilla.servicio.MaestroServicio;
import com.educacion.inedcuchilla.servicio.MateriaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/materias")
public class MateriasControlador {
    private final MateriaServicio materiaServicio;
    private final MaestroServicio maestroServicio;

    public MateriasControlador(MateriaServicio materiaServicio, MaestroServicio maestroServicio){
        this.materiaServicio = materiaServicio;
        this.maestroServicio = maestroServicio;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearMateria(@RequestBody MateriasDTO materiaDTO){
        Map<String, Object> respuesta = materiaServicio.crearMateria(materiaDTO);
        return ResponseEntity.ok(respuesta);
    }

}

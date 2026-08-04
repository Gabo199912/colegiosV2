package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.DTO.Maestros.MaestroRequest;
import com.educacion.inedcuchilla.Servicio.MaestroServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/maestro")
public class MaestroControlador {
    private final MaestroServicio maestroServicio;

    public MaestroControlador(MaestroServicio maestroServicio){
        this.maestroServicio = maestroServicio;
    }


    @PostMapping("/crear")
    public ResponseEntity<?> crearMaestro(@RequestBody MaestroRequest maestroRequest){
        Map<String, Object> respuesta = new HashMap<>();

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }
}

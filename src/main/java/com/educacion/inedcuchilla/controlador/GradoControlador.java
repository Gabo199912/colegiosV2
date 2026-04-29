package com.educacion.inedcuchilla.controlador;

import com.educacion.inedcuchilla.modelo.GradoModelo;
import com.educacion.inedcuchilla.servicio.GradoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grado")
public class GradoControlador {
    Map<String, Object> respuesta = new HashMap<>();
    private final GradoServicio gradoServicio;

    public GradoControlador(GradoServicio gradoServicio) {
        this.gradoServicio = gradoServicio;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarGrados(){
        List<GradoModelo> grados = gradoServicio.listarGrados();

        if (grados.isEmpty()){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "No hay grados registrados");
            return ResponseEntity.status(404).body(respuesta);
        }

        return ResponseEntity.ok(grados);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearGrado(@RequestBody GradoModelo gradoModelo){

        try {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta = gradoServicio.guardarGrado(gradoModelo);
            respuesta.put("OK", gradoModelo);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }catch (RuntimeException e){
            return ResponseEntity.status(409).body(e.getMessage());

        }
    }

}

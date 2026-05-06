package com.educacion.inedcuchilla.controlador;

import com.educacion.inedcuchilla.modelo.GradoModelo;
import com.educacion.inedcuchilla.servicio.GradoServicio;
import org.apache.coyote.Response;
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

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarGrado(@RequestBody GradoModelo gradoModelo){
        GradoModelo grado = gradoServicio.buscarPorId(gradoModelo.getIdGrado());
        if (grado == null){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "PARA ACTUALIZAR NO EL GRADO NO DEBE IR NULO");
            respuesta.put("STATUS", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(409).body(respuesta);
        }

        gradoServicio.guardarGrado(grado);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "El grado se actualizo correctamente.");
        respuesta.put("GRADO", grado);

        return ResponseEntity.ok(respuesta);
    }

}

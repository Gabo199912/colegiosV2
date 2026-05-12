package com.educacion.inedcuchilla.controlador;

import com.educacion.inedcuchilla.modelo.MaestroModelo;
import com.educacion.inedcuchilla.servicio.MaestroServicio;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/maestros")
public class MaestroControlador {
    public final MaestroServicio maestroServicio;

    public MaestroControlador(MaestroServicio maestroServicio){
        this.maestroServicio = maestroServicio;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarMaestros(){
        List<MaestroModelo> listaMaestros = maestroServicio.listarMaestros();

        if(listaMaestros == null){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "crea minimo un maestro");
            respuesta.put("Status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaMaestros);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearMaestro(@RequestBody MaestroModelo maestro){
        if (maestro == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError());
        }

        maestroServicio.crearMaestro(maestro);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "Maestro creado correctamente");
        respuesta.put("Status", maestro);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody MaestroModelo maestro){
        if (maestro == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError());
        }

        maestroServicio.crearMaestro(maestro);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "Maestro actualizado correctamente.");
        respuesta.put("Status", maestro);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

//    @PostMapping("/buscar-por-nombre/{nombreMaestro}")
//    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombreMaestro){
//        if (nombreMaestro.isEmpty()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError());
//        }
//
//
//    }

    public static Map<String, Object> responseError(){

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "Maestro actualizado correctamente.");
        respuesta.put("Status", HttpStatus.NOT_FOUND);

        return respuesta;
    }

}



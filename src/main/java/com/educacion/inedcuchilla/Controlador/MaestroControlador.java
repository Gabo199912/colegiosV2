package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.DTO.Maestros.ConvertirMaestro;
import com.educacion.inedcuchilla.DTO.Maestros.MaestroRequest;
import com.educacion.inedcuchilla.DTO.Maestros.MaestroResponse;
import com.educacion.inedcuchilla.Servicio.MaestroServicio;
import com.educacion.inedcuchilla.Servicio.MaestroServicioJDBC;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/maestro")
public class MaestroControlador {
    private final MaestroServicio maestroServicio;
    private final MaestroServicioJDBC maestroServicioJDBC;

    public MaestroControlador(MaestroServicio maestroServicio,
                              MaestroServicioJDBC maestroServicioJDBC){
        this.maestroServicio = maestroServicio;
        this.maestroServicioJDBC = maestroServicioJDBC;
    }


    @PostMapping("/crear")
    public ResponseEntity<?> crearMaestro(@Valid @RequestBody MaestroRequest maestroRequest){
            return maestroServicio.crearMaestro(maestroRequest);
    }

    @PatchMapping("/convertir")
    public ResponseEntity<Map<String, Object>> convertirMaestro(@Valid @RequestBody ConvertirMaestro maestro){
        return maestroServicio.convertirUsuarioMaestro(maestro);
    }


    @GetMapping("/listar")
    public ResponseEntity<?> listarMaestros(){
        Map<String, Object> respuesta = new HashMap<>();
        List<MaestroResponse> maestros = maestroServicioJDBC.listarMaestros();

        if (maestros.isEmpty()){
            respuesta.put("MENSAJE", "No se encontro ningun maestro, agregue uno e intente de nuevo.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        respuesta.put("MENSAJE", "Maestros encontrados");
        respuesta.put("MAESTROS", maestros);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}

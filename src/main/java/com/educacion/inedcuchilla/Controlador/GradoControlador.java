package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.DTO.Grado.GradoDTO;
import com.educacion.inedcuchilla.Servicio.GradoAcademicoServicio;
import com.educacion.inedcuchilla.Servicio.GradoAcademicoServicioJDBC;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grado")
public class GradoControlador {
    private final GradoAcademicoServicio gradoAcademicoServicio;
    private final GradoAcademicoServicioJDBC gradoAcademicoServicioJDBC;

    public GradoControlador(GradoAcademicoServicio gradoAcademicoServicio,
                            GradoAcademicoServicioJDBC gradoAcademicoServicioJDBC){
        this.gradoAcademicoServicio = gradoAcademicoServicio;
        this.gradoAcademicoServicioJDBC = gradoAcademicoServicioJDBC;
    }

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listarGrados(){
        Map<String, Object> respuesta = new HashMap<>();
        List<GradoDTO> grados = gradoAcademicoServicioJDBC.listarGrados();

        respuesta.put("MENSAJE", "GRADOS ENCONTRADOS");
        respuesta.put("GRADOS", grados);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearGrado(@RequestBody GradoDTO grado) {
        Map<String, Object> respuesta = new HashMap<>();
        if (grado.nombreGrado().isEmpty()) {
            respuesta.put("MENSAJE", "El grado no puede ir vacío");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        if (grado.nombreEspecialidad().isEmpty()){
            respuesta.put("MENSAJE", "La especialidad no puede ir vacía");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        if (grado.nombreSeccion().isEmpty()){
            respuesta.put("MENSAJE", "La sección no puede ir vacía");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        return gradoAcademicoServicio.crearGrado(grado);
    }

    @PatchMapping("/desactivar")
    public ResponseEntity<Map<String, Object>> desactivarGrado(@RequestBody GradoDTO grado){
        Map<String, Object> respuesta = new HashMap<>();

        if (grado.nombreSeccion().isEmpty()){
            respuesta.put("MENSAJE", "La sección no puede ir vacía");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        if (grado.nombreGrado().isEmpty()){
            respuesta.put("MENSAJE", "el grado no puede ir vacío");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        if (grado.nombreEspecialidad().isEmpty()){
            respuesta.put("MENSAJE", "La especialidad no puede ir vacía");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        return gradoAcademicoServicio.desactivarGradoAcademico(grado);
    }
}

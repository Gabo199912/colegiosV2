package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.DTO.Grado.GradoDTO;
import com.educacion.inedcuchilla.Servicio.GradoAcademicoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/grado")
public class GradoControlador {
    private final GradoAcademicoServicio gradoAcademicoServicio;

    public GradoControlador(GradoAcademicoServicio gradoAcademicoServicio){
        this.gradoAcademicoServicio = gradoAcademicoServicio;
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearGrado(GradoDTO grado) {
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

}

package com.educacion.inedcuchilla.controlador;

import com.educacion.inedcuchilla.DTO.AlumnoDTO;
import com.educacion.inedcuchilla.modelo.AlumnoModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.servicio.AlumnoServicio;
import com.educacion.inedcuchilla.servicio.UsuarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alumno")
public class AlumnoControlador {
    private final AlumnoServicio alumnoServicio;
    private final UsuarioServicio usuarioServicio;

    public AlumnoControlador(AlumnoServicio alumnoServicio, UsuarioServicio usuarioServicio) {
        this.alumnoServicio = alumnoServicio;
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarAlumnos(){
        Map<String, Object> alumnos = new HashMap<>();

        alumnos = alumnoServicio.listarAlumnosCompleto();

        if (alumnos.isEmpty()){
            Map<String, Object> respuesta = null;
            respuesta.put("ALUMNOS", "alumnos no encontrados");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        return ResponseEntity.status(HttpStatus.OK).body(alumnos);
    }

    @GetMapping("/buscar/{codigoAlumno}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String codigoAlumno){
        try{
            return ResponseEntity.ok(alumnoServicio.buscarPorCodigo(codigoAlumno));
        }catch (Exception e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}

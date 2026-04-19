package com.educacion.inedcuchilla.controlador;

import com.educacion.inedcuchilla.modelo.AlumnoModelo;
import com.educacion.inedcuchilla.servicio.AlumnoServicio;
import com.educacion.inedcuchilla.servicio.UsuarioServicio;
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

    public AlumnoControlador(AlumnoServicio alumnoServicio, UsuarioServicio usuarioServicio) {
        this.alumnoServicio = alumnoServicio;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarAlumnos(){
        List<AlumnoModelo> listaAlumnos = alumnoServicio.listarAlumnos();

        if (listaAlumnos.isEmpty()){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "No hay alumnos registrados");
            return ResponseEntity.status(404).body(respuesta);
        }

        return ResponseEntity.ok(listaAlumnos);
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

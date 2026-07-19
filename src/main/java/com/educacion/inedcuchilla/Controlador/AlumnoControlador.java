package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.DTO.Alumnos.AlumnoResponseDTO;
import com.educacion.inedcuchilla.DTO.Alumnos.AlumnoUsuarioRequestDTO;
import com.educacion.inedcuchilla.DTO.Alumnos.ConvertirAlumnoDTO;
import com.educacion.inedcuchilla.Servicio.AlumnoServicio;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/alumno")
public class AlumnoControlador {
    private final AlumnoServicio alumnoServicio;

    public AlumnoControlador(AlumnoServicio alumnoServicio){
        this.alumnoServicio = alumnoServicio;
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crarAlumno(@Valid @RequestBody AlumnoUsuarioRequestDTO alumnoUsuario){
        return alumnoServicio.crearAlumnoUsuario(alumnoUsuario);
    }

    @PatchMapping("/convertir")
    public ResponseEntity<Map<String, Object>> convertirAlumno(@Valid @RequestBody ConvertirAlumnoDTO convertirAlumno){
        return alumnoServicio.convertirAlumno(convertirAlumno);
    }



}

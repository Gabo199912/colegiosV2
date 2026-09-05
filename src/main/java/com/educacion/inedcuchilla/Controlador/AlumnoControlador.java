package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.DTO.Alumnos.AlumnoResponseDTO;
import com.educacion.inedcuchilla.DTO.Alumnos.AlumnoUsuarioRequestDTO;
import com.educacion.inedcuchilla.DTO.Alumnos.ConvertirAlumnoDTO;
import com.educacion.inedcuchilla.Servicio.AlumnoServicio;
import com.educacion.inedcuchilla.Servicio.AlumnoServicioJDBC;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/alumno")
public class AlumnoControlador {
    private final AlumnoServicio alumnoServicio;

    public AlumnoControlador(AlumnoServicio alumnoServicio){
        this.alumnoServicio = alumnoServicio;
    }

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listarAlumno(){
        return alumnoServicio.listarAlumnos();
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crarAlumno(@Valid @RequestBody AlumnoUsuarioRequestDTO alumnoUsuario){
        return alumnoServicio.crearAlumnoUsuario(alumnoUsuario);
    }

    @PatchMapping("/convertir")
    public ResponseEntity<Map<String, Object>> convertirAlumno(@Valid @RequestBody ConvertirAlumnoDTO convertirAlumno){
        return alumnoServicio.convertirAlumno(convertirAlumno);
    }

    @PostMapping("/cargar-masivo")
    public ResponseEntity<Map<String, Object>> cargarMasivamentePDF(@RequestParam("archivo")MultipartFile archivo){
        Map<String,Object> respuesta = new HashMap<>();
        try{

            if (archivo.isEmpty()){
                respuesta.put("MENSAJE", "El archivo esta vacío, carga uno nuevo.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }

            return alumnoServicio.cargarMasivo(archivo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

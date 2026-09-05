package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.DTO.Materias.MateriaRequest;
import com.educacion.inedcuchilla.Servicio.MateriaServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/materias")
public class MateriaControlador {
    private final MateriaServicio materiaServicio;

    public MateriaControlador(MateriaServicio materiaServicio){
        this.materiaServicio = materiaServicio;
    }


    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearMateria(@Valid @RequestBody MateriaRequest materia){
        return materiaServicio.crearMateria(materia);
    }

//    @GetMapping("/prueba")
//    public void pruebaExcel(@RequestParam("MATERIAS") MultipartFile archivo){
//        try {
//            materiaServicio.crearMateria(archivo);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}

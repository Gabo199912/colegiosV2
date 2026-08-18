package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.Servicio.MateriaServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/materias")
public class MateriaControlador {
    private final MateriaServicio materiaServicio;

    public MateriaControlador(MateriaServicio materiaServicio){
        this.materiaServicio = materiaServicio;
    }

    @GetMapping("/prueba")
    public void pruebaExcel(@RequestParam("MATERIAS") MultipartFile archivo){
        try {
            materiaServicio.crearMateria(archivo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

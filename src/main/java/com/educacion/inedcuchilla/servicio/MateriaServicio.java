package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.DTO.MateriasDTO;
import com.educacion.inedcuchilla.modelo.MaestroModelo;
import com.educacion.inedcuchilla.modelo.MateriasModelo;
import com.educacion.inedcuchilla.repositorio.MaestroRepositorio;
import com.educacion.inedcuchilla.repositorio.MateriaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class MateriaServicio {
    private final MateriaRepositorio materiaRepositorio;
    private final MaestroRepositorio maestroRepositorio;

    public MateriaServicio(MateriaRepositorio materiaRepositorio,
                           MaestroRepositorio maestroRepositorio) {
        this.materiaRepositorio = materiaRepositorio;
        this.maestroRepositorio = maestroRepositorio;
    }

    @Transactional
    public Map<String, Object> crearMateria(MateriasDTO materiaDTO){
       boolean existe = maestroRepositorio.existsByCodigoEmpleado(materiaDTO.getCodigoMaestro());
       boolean existeMateria = materiaRepositorio.existsByNombreMateria(materiaDTO.getMateria().getNombreMateria());
        if (!existe) {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "el empleado debe existir antes de registrar materia");
            return respuesta;
        }

        if (existeMateria) {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "No puedes crear 2 materias con el mismo nombre");
            return respuesta;
        }



        MaestroModelo maestro = maestroRepositorio.findByCodigoEmpleado(materiaDTO.getCodigoMaestro());

        MateriasModelo materia = new MateriasModelo();
        materia.setNombreMateria(materiaDTO.getMateria().getNombreMateria());
        materia.setMaestro(maestro);

        materiaRepositorio.save(materia);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "Materia agregada correctamente");
        respuesta.put("MATERIA", materia);
        return respuesta;
    }

    public Map<String, Object> actualizarMateria(MateriasModelo materiasModelo){

        materiaRepositorio.save(materiasModelo);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "Materia agregada correctamente");
        respuesta.put("MATERIA", materiasModelo);
        return respuesta;
    }
}

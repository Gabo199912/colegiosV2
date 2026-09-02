package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Grado.GradoDTO;
import com.educacion.inedcuchilla.Modelo.EspecialidadModelo;
import com.educacion.inedcuchilla.Modelo.GradoAcademicoModelo;
import com.educacion.inedcuchilla.Modelo.GradoModelo;
import com.educacion.inedcuchilla.Modelo.SeccionModelo;
import com.educacion.inedcuchilla.repositorio.EspecialidadRepositorio;
import com.educacion.inedcuchilla.repositorio.GradoAcademicoRepositorio;
import com.educacion.inedcuchilla.repositorio.GradoRepositorio;
import com.educacion.inedcuchilla.repositorio.SeccionRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class GradoAcademicoServicio {
    private final GradoRepositorio gradoRepositorio;
    private final EspecialidadRepositorio especialidadRepositorio;
    private final SeccionRepositorio seccionRepositorio;
    private final GradoAcademicoRepositorio gradoAcademicoRepositorio;
    private final GradoAcademicoServicioJDBC gradoAcademicoServicioJDBC;


    public GradoAcademicoServicio(GradoRepositorio gradoRepositorio,
                                  EspecialidadRepositorio especialidadRepositorio,
                                  SeccionRepositorio seccionRepositorio,
                                  GradoAcademicoRepositorio gradoAcademicoRepositorio, GradoAcademicoServicioJDBC gradoAcademicoServicioJDBC){
        this.gradoRepositorio = gradoRepositorio;
        this.especialidadRepositorio = especialidadRepositorio;
        this.seccionRepositorio = seccionRepositorio;
        this.gradoAcademicoRepositorio = gradoAcademicoRepositorio;
        this.gradoAcademicoServicioJDBC = gradoAcademicoServicioJDBC;
    }


    public ResponseEntity<Map<String, Object>> desactivarGradoAcademico(GradoDTO grado){
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Integer> idGradoAcademico = gradoAcademicoServicioJDBC.buscarIdGradoAcadmico(grado);

        if (idGradoAcademico.isEmpty()){
            respuesta.put("MENSAJE", "el grado ingresado no existe.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        GradoAcademicoModelo gradoEncontrado = gradoAcademicoRepositorio.findByIdGradoAcademico(idGradoAcademico.get());
        gradoEncontrado.setActivo(false);

        gradoAcademicoRepositorio.save(gradoEncontrado);
        respuesta.put("MENSAJE", "El grado se desactivo correctamente.");
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> crearGrado(GradoDTO gradoVerificar){
        Map<String, Object> respuesta = new HashMap<>();

        GradoModelo grado = validarGrado(gradoVerificar);
        EspecialidadModelo especialidad = validarEspecialidad(gradoVerificar);
        SeccionModelo seccion = validarSeccion(gradoVerificar);

        if (gradoAcademicoRepositorio.existsGradoAcademicoModeloByEspecialidadIdEspecialidadAndGradoIdGradoAndSeccionIdSeccion(especialidad.getIdEspecialidad(), grado.getIdGrado(), seccion.getIdSeccion())){
            respuesta.put("MENSAJE", "ese grado ya existe, cree uno que no exista");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        GradoAcademicoModelo gradoCompleto = new GradoAcademicoModelo();
        gradoCompleto.setGrado(grado);
        gradoCompleto.setSeccion(seccion);
        gradoCompleto.setEspecialidad(especialidad);

        gradoAcademicoRepositorio.save(gradoCompleto);

        GradoDTO gradoAcademicoGuardado = new GradoDTO(
                grado.getGrado(),
                seccion.getSeccion(),
                especialidad.getNombreEspecialidad()
        );

        respuesta.put("MENSAJE", "se guardo el grado correctamente.");
        respuesta.put("GRADO", gradoAcademicoGuardado);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    public GradoModelo validarGrado(GradoDTO gradoVerificar){
        if (!gradoRepositorio.existsByGrado(gradoVerificar.nombreGrado())){
            GradoModelo nuevoGrado = new GradoModelo();
            nuevoGrado.setGrado(gradoVerificar.nombreGrado());
            return gradoRepositorio.save(nuevoGrado);
        }else {
            return gradoRepositorio.findByGrado(gradoVerificar.nombreGrado());
        }
    }

    public EspecialidadModelo validarEspecialidad(GradoDTO gradoVerificar){
        if (!especialidadRepositorio.existsByNombreEspecialidad(gradoVerificar.nombreEspecialidad())){
            EspecialidadModelo nuevaEspecialidad = new EspecialidadModelo();
            nuevaEspecialidad.setNombreEspecialidad(gradoVerificar.nombreEspecialidad());
            return especialidadRepositorio.save(nuevaEspecialidad);
        }else {
            return especialidadRepositorio.findByNombreEspecialidad(gradoVerificar.nombreEspecialidad());
        }
    }

    public SeccionModelo validarSeccion(GradoDTO gradoVerificar){
        if (!seccionRepositorio.existsBySeccion(gradoVerificar.nombreSeccion())){
            SeccionModelo nuevaSeccion = new SeccionModelo();
            nuevaSeccion.setSeccion(gradoVerificar.nombreSeccion());
            return seccionRepositorio.save(nuevaSeccion);
        }else {
            return seccionRepositorio.findBySeccion(gradoVerificar.nombreSeccion());
        }

    }

}

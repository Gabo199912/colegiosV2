package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.EspecialidadModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepositorio extends JpaRepository<EspecialidadModelo, Integer> {
    Boolean findByNombreEspecialidad(String nombreEspecialidad);
}

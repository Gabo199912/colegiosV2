package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.Modelo.GradoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradoRepositorio extends JpaRepository<GradoModelo, Integer> {
    Boolean existsByGrado(String nombreGrado);
    GradoModelo findByGrado(String nombreGrado);

}

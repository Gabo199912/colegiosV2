package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.GradoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.DocFlavor;

@Repository
public interface GradoRepositorio extends JpaRepository<GradoModelo, Integer> {
    Boolean existsByGrado(String nombreGrado);
    GradoModelo findByGrado(String nombreGrado);

}

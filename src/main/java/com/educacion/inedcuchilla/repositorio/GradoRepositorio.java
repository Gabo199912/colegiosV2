package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.GradoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradoRepositorio extends JpaRepository<GradoModelo,  Integer> {
    GradoModelo findById(int id);

}

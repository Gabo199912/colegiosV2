package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.InscripcionModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepositorio extends JpaRepository<InscripcionModelo, Integer> {
}

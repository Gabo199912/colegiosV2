package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.Modelo.InscripcionModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepositorio extends JpaRepository<InscripcionModelo, Integer> {
}

package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.AlumnoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepositorio extends JpaRepository<AlumnoModelo, Integer> {
}

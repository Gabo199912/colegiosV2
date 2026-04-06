package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.AlumnoModelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepositorio extends JpaRepository<AlumnoModelo, Integer> {
}

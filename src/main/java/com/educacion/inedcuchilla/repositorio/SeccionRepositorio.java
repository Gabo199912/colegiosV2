package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.Modelo.SeccionModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeccionRepositorio extends JpaRepository<SeccionModelo, Integer> {
    Boolean existsBySeccion(String nombreSeccion);
    SeccionModelo findBySeccion(String nombreSeccion);
}

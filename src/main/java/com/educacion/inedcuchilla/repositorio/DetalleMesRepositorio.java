package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.DetalleMesModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleMesRepositorio extends JpaRepository<DetalleMesModelo, Integer> {
}

package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.DetallePagoMesModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePagoMesRepositorio extends JpaRepository<DetallePagoMesModelo, Integer> {
}

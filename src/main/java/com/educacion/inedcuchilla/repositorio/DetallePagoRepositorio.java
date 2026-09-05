package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.Modelo.DetallePagoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePagoRepositorio extends JpaRepository<DetallePagoModelo, Integer> {
}

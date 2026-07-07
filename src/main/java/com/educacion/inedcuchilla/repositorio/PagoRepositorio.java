package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.PagoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepositorio extends JpaRepository<PagoModelo, Integer> {
}

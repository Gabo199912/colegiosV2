package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.PagosModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagosRepositorio extends JpaRepository<PagosModelo, Integer> {
    PagosModelo findByTipoPago(String tipoPago);
    PagosModelo findByIdPago(Integer idPago);
    boolean existsByTipoPago(String tipoPago);
}

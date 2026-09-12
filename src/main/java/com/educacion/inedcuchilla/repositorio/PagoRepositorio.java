package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.Modelo.PagoModelo;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagoRepositorio extends JpaRepository<PagoModelo, Integer> {
    PagoModelo findByIdPago(Integer idPago);

    boolean existsByTipoPago(String tipoPago);

    Optional<PagoModelo> findByTipoPago(String tipoPago);
}

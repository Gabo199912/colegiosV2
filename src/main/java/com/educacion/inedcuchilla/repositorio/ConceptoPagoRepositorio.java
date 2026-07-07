package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.ConceptoPagoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptoPagoRepositorio extends JpaRepository<ConceptoPagoModelo, Integer> {
}

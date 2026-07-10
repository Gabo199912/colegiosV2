package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.PagoExtraModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoExtraRepositorio extends JpaRepository<PagoExtraModelo, Integer> {
    PagoExtraModelo findByIdPagoExtra(Integer idPagoExtra);
}

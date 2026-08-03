package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.CicloEscolarModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CicloEscolarRepositorio extends JpaRepository<CicloEscolarModelo, Integer> {
    CicloEscolarModelo findByAnio(Integer anio);
}

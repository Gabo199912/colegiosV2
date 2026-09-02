package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.Modelo.MaestroModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroRepositorio extends JpaRepository<MaestroModelo, Integer> {
}

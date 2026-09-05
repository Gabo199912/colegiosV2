package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.Modelo.MaestroMateriaModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroMateriaRepositorio extends JpaRepository<MaestroMateriaModelo, Integer> {
}

package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.MateriaModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepositorio extends JpaRepository<MateriaModelo, Integer> {
}

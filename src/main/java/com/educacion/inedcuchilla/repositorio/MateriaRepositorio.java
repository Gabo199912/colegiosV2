package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.MateriasModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepositorio extends JpaRepository<MateriasModelo, Integer> {
}

package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.EncargadoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncargadoRepositorio extends JpaRepository<EncargadoModelo, Integer> {

}

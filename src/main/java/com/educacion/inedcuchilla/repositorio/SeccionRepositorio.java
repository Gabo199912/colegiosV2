package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.SeccionModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeccionRepositorio extends JpaRepository<SeccionModelo, Integer> {

}

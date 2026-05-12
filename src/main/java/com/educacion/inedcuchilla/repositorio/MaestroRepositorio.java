package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.MaestroModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaestroRepositorio extends JpaRepository<MaestroModelo,Integer > {
    List<MaestroModelo> findAll();
}

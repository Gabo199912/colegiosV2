package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.Modelo.DetalleMesModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleMesRepositorio extends JpaRepository<DetalleMesModelo, Integer> {
    List<DetalleMesModelo> findAllById(Iterable<Integer> ids);

}

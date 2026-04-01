package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.DetallePagoModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DetallePagoRepositorio extends JpaRepository<DetallePagoModelo, Integer> {
    List<DetallePagoModelo> findByUsuario(UsuarioModelo usuario);
}

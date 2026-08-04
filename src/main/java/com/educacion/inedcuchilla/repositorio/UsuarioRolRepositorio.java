package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.modelo.UsuarioRolModelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRolRepositorio extends JpaRepository<UsuarioRolModelo, Integer> {

    List<UsuarioRolModelo> findAllByUsuario(UsuarioModelo usuario);

    UsuarioRolModelo usuario(UsuarioModelo usuario);r
}

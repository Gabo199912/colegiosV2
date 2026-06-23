package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.RolModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepositorio extends JpaRepository<RolModelo, Integer> {
    RolModelo findByTipoUsuario(String tipoUsuario);
    RolModelo findByIdRol(Integer idRol);
    boolean existsByIdRol(Integer idRol);
    boolean existsByTipoUsuario(String tipoUsuario);
    List<RolModelo> findAll();

}

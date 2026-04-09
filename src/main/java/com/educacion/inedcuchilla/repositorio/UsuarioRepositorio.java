package com.educacion.inedcuchilla.repositorio;


import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioModelo, Integer> {
    boolean existsByEmail(String email);
    boolean existsByNombre(String nombre);
    boolean existsByApellido(String apellido);
    boolean existsByNombreUsuario(String nombreUsuario);

    UsuarioModelo findByEmail(String email);
    Optional<UsuarioModelo> findByNombreUsuario(String nombreUsuario);
    Optional<UsuarioModelo> findByNombre(String nombre);
    UsuarioModelo findByApellido(String apellido);
    UsuarioModelo findByIdUsuario(Integer idUsuario);


    @Query("SELECT u.nombreUsuario FROM UsuarioModelo u")
    List<String> cargarNombrsUsuario();


    @Query("SELECT u.email FROM UsuarioModelo u")
    List<String> cargarCorreo();
}

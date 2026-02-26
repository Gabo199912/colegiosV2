package com.educacion.inedcuchilla.repositorio;


import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioModelo, Integer> {
    boolean existsByEmail(String email);
    boolean existsByNombre(String nombre);
    boolean existsByApellido(String apellido);


    UsuarioModelo findByEmail(String email);
    UsuarioModelo findByNombre(String nombre);
    UsuarioModelo findByApellido(String apellido);

}

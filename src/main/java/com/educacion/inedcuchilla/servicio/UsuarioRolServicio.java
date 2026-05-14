package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.modelo.UsuarioRolModelo;
import com.educacion.inedcuchilla.repositorio.UsuarioRolRepositorio;
import org.springframework.stereotype.Service;

@Service
public class UsuarioRolServicio {
    private final UsuarioRolRepositorio usuarioRolRepositorio;

    public UsuarioRolServicio(UsuarioRolRepositorio usuarioRolRepositorio){
        this.usuarioRolRepositorio = usuarioRolRepositorio;
    }

    public void guardarUsuarioRol(UsuarioRolModelo usuarioRolModelo){
        usuarioRolRepositorio.save(usuarioRolModelo);
    }
}

package com.educacion.inedcuchilla.servicio;


import com.educacion.inedcuchilla.SeguridadConfig.SecurityConfig;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public List<UsuarioModelo> listarUsuarios(){
        return usuarioRepositorio.findAll();
    }

    public UsuarioModelo guardarUsuario(UsuarioModelo usuarioModelo){

        return usuarioRepositorio.save(usuarioModelo);
    }

    public void eliminarUsuario(UsuarioModelo usuarioModelo){
    }

    public UsuarioModelo buscarUsuarioPorEmail(String email){
        return usuarioRepositorio.findByEmail(email);
    }

    public UsuarioModelo buscarPorIdUsuario(Integer idUsuario){
        return usuarioRepositorio.findByIdUsuario(idUsuario);
    }

    public boolean existeUsuarioPorNombre(String nombre){
        return usuarioRepositorio.existsByNombre(nombre);
    }

    public boolean existeUsuarioPorEmail(String email){
        return usuarioRepositorio.existsByEmail(email);
    }

    public Optional<UsuarioModelo> buscarUsuarioPorNombre(String nombre){
        return usuarioRepositorio.findByNombre(nombre);
    }

    public UsuarioModelo buscarUsuarioPorApellido(String apellido){
        return usuarioRepositorio.findByApellido(apellido);
    }
}

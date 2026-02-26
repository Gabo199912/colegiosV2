package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.repositorio.RolRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServicio {

    private final RolRepositorio rolRepositorio;

    public RolServicio(RolRepositorio rolRepositorio) {
        this.rolRepositorio = rolRepositorio;
    }

    public boolean existePorId(Integer id){
        return rolRepositorio.existsById(id);
    }

    public List<RolModelo> listarRoles(){
        return rolRepositorio.findAll();
    }

    public RolModelo guardarRol(RolModelo rol){
        rolRepositorio.save(rol);
        return rol;
    }

    public void eliminarRol(RolModelo rol){
        rolRepositorio.delete(rol);
    }

    public RolModelo buscarRolPorId(Integer id){
        return rolRepositorio.findById(id).orElse(null);
    }

    public RolModelo buscarRolPorNombre(String nombre){
        return rolRepositorio.findByTipoUsuario(nombre);
    }

    public boolean existeRolPorNombre(String nombre){
        return rolRepositorio.existsByTipoUsuario(nombre);
    }

}

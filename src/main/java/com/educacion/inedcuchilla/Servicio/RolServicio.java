package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.repositorio.RolRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServicio {
    private final RolRepositorio rolRepositorio;

    public RolServicio(RolRepositorio rolRepositorio){
        this.rolRepositorio = rolRepositorio;
    }

    public List<RolModelo> listarRoles(){
        List<RolModelo> listaRoles = rolRepositorio.findAll();
        return listaRoles;
    }
}

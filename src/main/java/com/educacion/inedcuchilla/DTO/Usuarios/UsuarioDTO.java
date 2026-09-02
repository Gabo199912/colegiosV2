package com.educacion.inedcuchilla.DTO.Usuarios;

import com.educacion.inedcuchilla.Modelo.RolModelo;
import com.educacion.inedcuchilla.Modelo.UsuarioModelo;

import java.util.List;

public class UsuarioDTO {
    private UsuarioModelo usuario;
    private List<RolModelo> roles;

    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }

    public List<RolModelo> getRoles() {
        return roles;
    }

    public void setRoles(List<RolModelo> roles) {
        this.roles = roles;
    }


}

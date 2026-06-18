package com.educacion.inedcuchilla.DTO;

import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;

import java.time.LocalDate;
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

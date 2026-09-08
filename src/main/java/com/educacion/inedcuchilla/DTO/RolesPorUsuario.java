package com.educacion.inedcuchilla.DTO;

import java.util.List;

public record RolesPorUsuario(
        Integer idRol,
        String tipoUsuario
) {
    public RolesPorUsuario(Integer idRol, String tipoUsuario) {
        this.idRol = idRol;
        this.tipoUsuario = tipoUsuario;
    }
}

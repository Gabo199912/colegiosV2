package com.educacion.inedcuchilla.DTO.Roles;

import java.util.List;

public record AsignarRoles(
        String nombreUsuario,
        List<Integer> idRoles
) {
}

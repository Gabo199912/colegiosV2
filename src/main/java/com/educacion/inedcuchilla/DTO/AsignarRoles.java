package com.educacion.inedcuchilla.DTO;

import java.util.List;

public record AsignarRoles(
        String nombreUsuario,
        List<Integer> idRoles
) {
}

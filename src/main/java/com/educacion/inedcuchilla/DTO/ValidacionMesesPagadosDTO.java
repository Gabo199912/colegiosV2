package com.educacion.inedcuchilla.DTO;

public record ValidacionMesesPagadosDTO(
        String nombreUsuario,
        String tipoPago,
        Boolean pagado,
        Integer idMes,
        String nombreMes
) {
}

package com.educacion.inedcuchilla.DTO.Pagos;

public record ValidacionMesesPagadosDTO(
        String nombreUsuario,
        String tipoPago,
        Boolean pagado,
        Integer idMes,
        String nombreMes
) {
}

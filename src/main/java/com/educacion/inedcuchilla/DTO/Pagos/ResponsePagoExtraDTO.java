package com.educacion.inedcuchilla.DTO.Pagos;

import java.math.BigDecimal;

public record ResponsePagoExtraDTO(
        String nombreUsuario,
        String tipoPago,
        String metodoPago,
        String descripcion,
        BigDecimal total
) {
}

package com.educacion.inedcuchilla.DTO.Pagos;

import java.math.BigDecimal;

public record PagoExtraDTO(
        BigDecimal total,
        String descripcion,
        Integer idUsuario,
        Integer idPago,
        Integer idPagoExtra
) {
}

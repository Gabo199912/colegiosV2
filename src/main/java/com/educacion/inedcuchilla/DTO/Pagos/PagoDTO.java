package com.educacion.inedcuchilla.DTO.Pagos;

import java.math.BigDecimal;
import java.util.List;

public record PagoDTO(
        BigDecimal total,
        String descripcion,
        Integer idUsuario,
        Integer idPago,
        List<Integer> meses
) {
}

package com.educacion.inedcuchilla.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PagoDTO(
        BigDecimal total,
        String descripcion,
        Integer idUsuario,
        Integer idPago,
        List<Integer> meses
) {
}

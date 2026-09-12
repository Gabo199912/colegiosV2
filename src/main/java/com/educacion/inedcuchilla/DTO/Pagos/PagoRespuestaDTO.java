package com.educacion.inedcuchilla.DTO.Pagos;

import com.educacion.inedcuchilla.DTO.Meses.MesesDTO;

import java.math.BigDecimal;
import java.util.List;

public record PagoRespuestaDTO(
        String nombreUsuario,
        String tipoPago,
        List<MesesDTO> meses,
        BigDecimal total,
        String descripcion
) {
}

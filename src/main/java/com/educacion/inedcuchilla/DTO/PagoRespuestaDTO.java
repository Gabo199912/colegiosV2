package com.educacion.inedcuchilla.DTO;

import com.educacion.inedcuchilla.modelo.UsuarioModelo;

import java.math.BigDecimal;
import java.util.List;

public record PagoRespuestaDTO(
        String nombreUsuario,
        String tipoPago,
        List<String> meses,
        BigDecimal total,
        String descripcion
) {
}

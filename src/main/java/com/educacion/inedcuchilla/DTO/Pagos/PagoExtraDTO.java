package com.educacion.inedcuchilla.DTO.Pagos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PagoExtraDTO(

        @NotNull(message = "el total no puede estar vacio.")
        BigDecimal total,

        @NotBlank(message = "ingrese una descripcion para saber el porque del pago.")
        String descripcion,

        @NotBlank(message = "El nombre de usuario no puede estar vacio.")
        String nombreUsuario,

        @NotBlank(message = "el email no puede estar vacio.")
        String correoUsuario,

        @NotBlank(message = "el tipo de pago no puede estar vacio.")
        String tipoPago,

        @NotBlank(message = "el por que del pago no puede estar vacio.")
        String pagoExtra
) {
}

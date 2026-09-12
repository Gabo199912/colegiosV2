package com.educacion.inedcuchilla.DTO.Pagos;

import com.educacion.inedcuchilla.DTO.Meses.MesesDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record PagoDTO(

        @NotNull(message = "El total no puede ir vacio.")
        BigDecimal total,

        @NotBlank(message = "La descripción no puede estar vacío")
        String descripcion,

        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        String nombreUsuario,

        @NotBlank(message = "El correo no puede estar vacío")
        String correoUsuario,

        @NotBlank(message = "El tipo de pago no puede estar vacío")
        String tipoDePago,

        @NotNull(message = "Los meses no pueden estar vacíos.")
        List<MesesDTO> meses
) {
}

package com.educacion.inedcuchilla.DTO.Maestros;

import jakarta.validation.constraints.NotBlank;

public record ConvertirMaestro(

        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        String nombreUsuario,

        @NotBlank(message = "El codigo de empleado no puede estar vacío")
        String codigoEmpleado
) {
}

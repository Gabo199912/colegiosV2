package com.educacion.inedcuchilla.DTO.Alumnos;

import jakarta.validation.constraints.NotBlank;

public record ConvertirAlumnoDTO(

        @NotBlank(message = "El nombre de usuario es obligatorio.")
        String nombreUsuario,

        @NotBlank(message = "El codigo alumno es obligatorio")
        String codigoAlumno,

        @NotBlank(message = "El genero es obligatorio.")
        String genero
) {
}

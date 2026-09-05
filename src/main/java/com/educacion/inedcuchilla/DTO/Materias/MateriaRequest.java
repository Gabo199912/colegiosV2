package com.educacion.inedcuchilla.DTO.Materias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MateriaRequest(

        @NotBlank(message = "el nombre de la materia es obligatorio.")
        @NotNull(message = "el nombre de la materia es obligarotio.")
        String nombreMateria,

        @NotBlank(message = "el nombre de la especialidad es obligatorio.")
        @NotNull(message = "el nombre de la especialidad es obligatorio.")
        String especialidad,

        @NotBlank(message = "el nombre del grado es obligatorio.")
        @NotNull(message = "el nombre del grado es obligatorio.")
        String grado,

        @NotBlank(message = "el codigo del profesor es obligatorio.")
        @NotNull(message = "el codigo del profesor es obligatorio.")
        String codigoProfesor
) {
}

package com.educacion.inedcuchilla.DTO.Materias;

import com.educacion.inedcuchilla.modelo.GradoAcademicoModelo;

public record MateriaRequest(
        String nombreMateria,
        GradoAcademicoModelo gradoAcademico
) {
}

package com.educacion.inedcuchilla.DTO.Materias;

import com.educacion.inedcuchilla.modelo.GradoAcademicoModelo;

public record MateriaRequest(
        String nombreMateria,
        String especialidad,
        String seccion,
        String grado,
        String codigoProfesor
) {
}

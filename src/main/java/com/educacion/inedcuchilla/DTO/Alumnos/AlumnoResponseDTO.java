package com.educacion.inedcuchilla.DTO.Alumnos;

public record AlumnoResponseDTO (
        String nombreUsuario,
        String nombre,
        String email,
        String telefono,
        String codigoAlumno
) {
}

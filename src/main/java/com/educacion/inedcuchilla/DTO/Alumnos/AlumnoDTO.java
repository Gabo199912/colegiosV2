package com.educacion.inedcuchilla.DTO.Alumnos;

import java.time.LocalDate;

public record AlumnoDTO(
        String nombreUsuario,
        String nombre,
        String apellido,
        String codigoAlumno,
        LocalDate fechaNacimiento,
        String genero,
        String email,
        String contrasenia
) {
}

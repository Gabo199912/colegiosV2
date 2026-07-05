package com.educacion.inedcuchilla.DTO;

import java.time.LocalDate;

public record AlumnoUsuarioDTO(
        String nombreUsuario,
        String nombre,
        String apellido,
        String email,
        String telefono,
        LocalDate fechaNacimiento,
        String contrasenia,
        String codigoAlumno,
        String genero
) {
}

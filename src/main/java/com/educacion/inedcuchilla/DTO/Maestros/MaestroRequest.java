package com.educacion.inedcuchilla.DTO.Maestros;

import java.time.LocalDate;

public record MaestroRequest (
        String nombreUsuario,
        String nombre,
        String apellido,
        String email,
        String telefono,
        LocalDate fechaNacimiento,
        String contrasenia,
        String codigoEmpleado
){
}

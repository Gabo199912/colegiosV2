package com.educacion.inedcuchilla.DTO.Usuarios;

import java.time.LocalDate;
import java.util.List;

public record UsuarioRecordDTO(
    String nombreUsuario,
    String nombre,
    String apellido,
    String email,
    String telefono,
    LocalDate fechaNacimiento,
    String contrasenia,
    List<Integer> idRoles
) {

}

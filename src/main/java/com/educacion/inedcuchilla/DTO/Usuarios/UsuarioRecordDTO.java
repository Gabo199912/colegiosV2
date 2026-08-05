package com.educacion.inedcuchilla.DTO.Usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.List;

public record UsuarioRecordDTO(

        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        String nombreUsuario,

        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        String nombre,

        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        String apellido,

        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        String email,

        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        String telefono,

        @NotNull(message = "la fecha de nacimiento no puede estar vacía")
        @Past(message = "La fecha de nacimiento no puede ser hoy")
        LocalDate fechaNacimiento,

        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        String contrasenia,

        List<Integer> idRoles
) {

}

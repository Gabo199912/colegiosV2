package com.educacion.inedcuchilla.DTO.Alumnos;



import jakarta.validation.constraints.*;

import java.time.LocalDate;
public record AlumnoUsuarioRequestDTO(

        @NotBlank(message = "El nombre de usuario es obligatorio.")
        String nombreUsuario,

        @NotBlank(message = "El nombre del usuario es obligatorio.")
        String nombre,

        @NotBlank(message = "El apellido de usuario es obligatorio.")
        String apellido,

        @NotBlank(message = "El correo del usuario es obligatorio.")
        @Email
        String email,

        @NotBlank(message = "El telefono del usuario es obligatorio.")
        String telefono,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento no puede ser hoy")
        LocalDate fechaNacimiento,

        @NotBlank(message = "La constraseña es obligatoria.")
        @Size(min = 7, message = "la contraseña debe tener minimo 7 caracteres")
        String contrasenia,

        @NotBlank(message = "El codigo de alumno es obligatorio.")
        String codigoAlumno,

        @NotBlank(message = "El genero de usuario es obligatorio.")
        String genero
) {
}


package com.educacion.inedcuchilla.DTO.Maestros;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record MaestroRequest (

        @NotBlank(message = "El nombre de usuario no puede ir vacío")
        String nombreUsuario,

        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @NotBlank(message = "El apellido no puede estar vacío")
        String apellido,

        @NotBlank(message = "El email no puede estar vacío")
        String email,

        @NotBlank(message = "El telefono no puede estar vacío")
        String telefono,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento no puede ser hoy")
        LocalDate fechaNacimiento,

        @NotBlank(message = "La contraseña no puede estar vacía")
        String contrasenia,

        @NotBlank(message = "El codigo de empleado no puede estar vacío")
        String codigoEmpleado
){
}

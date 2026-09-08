package com.educacion.inedcuchilla.DTO;

import java.time.LocalDate;

public record UsuarioConRolRecordDTO(
         String nombreUsuario,
         String nombre,
         String apellido,
         String email,
         String telefono,
         LocalDate fechaNacimiento,
         String rol
         ) {

    public UsuarioConRolRecordDTO(String nombreUsuario, String nombre, String apellido, String email, String telefono, LocalDate fechaNacimiento, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.rol = rol;
    }
}

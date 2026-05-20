package com.educacion.inedcuchilla.DTO;

public class MaestroJdbcDTO {
    private String nombreUsuario;
    private String nombre;
    private String telefono;
    private String email;
    private String tipoUsuario;

    public MaestroJdbcDTO(String nombreUsuario, String nombre, String telefono, String email, String tipoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

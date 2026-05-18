package com.educacion.inedcuchilla.DTO;

public class UsuariosConRolDTO {
    String nombreUsuario;
    String email;
    String tipoUsuario;

    public UsuariosConRolDTO(String nombreUsuario, String email, String tipoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_rol")
public class UsuarioRolModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuarioRol;


    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioModelo usuario;

    @ManyToOne
    @JoinColumn(name = "fk_id_rol")
    private RolModelo roles;


    public Integer getIdUsuarioRol() {
        return idUsuarioRol;
    }

    public void setIdUsuarioRol(Integer idUsuarioRol) {
        this.idUsuarioRol = idUsuarioRol;
    }

    public RolModelo getRoles() {
        return roles;
    }

    public void setRoles(RolModelo roles) {
        this.roles = roles;
    }

    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }
}

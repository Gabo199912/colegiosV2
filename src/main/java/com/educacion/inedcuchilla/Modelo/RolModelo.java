package com.educacion.inedcuchilla.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rol")
public class RolModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "tipo_usuario")
    private String tipoUsuario;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private Boolean estado;

    @JsonIgnore
    @OneToMany(mappedBy = "roles")
    private List<UsuarioRolModelo> usuarioRolModelo;

    public RolModelo() {
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<UsuarioRolModelo> getUsuarioRolModelo() {
        return usuarioRolModelo;
    }

    public void setUsuarioRolModelo(List<UsuarioRolModelo> usuarioRolModelo) {
        this.usuarioRolModelo = usuarioRolModelo;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }



    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<UsuarioRolModelo> getUsuarioRol() {
        return usuarioRolModelo;
    }

    public void setUsuarioRol(List<UsuarioRolModelo> usuarioRolModelo) {
        this.usuarioRolModelo = usuarioRolModelo;
    }
}

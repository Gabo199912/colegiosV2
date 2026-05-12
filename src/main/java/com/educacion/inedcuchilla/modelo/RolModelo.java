package com.educacion.inedcuchilla.modelo;

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

    @Column(name = "estado")
    private Boolean estado;

    @OneToMany(mappedBy = "roles")
    private List<UsuarioRol> usuarioRol;

    public RolModelo() {
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

    public List<UsuarioRol> getUsuarioRol() {
        return usuarioRol;
    }

    public void setUsuarioRol(List<UsuarioRol> usuarioRol) {
        this.usuarioRol = usuarioRol;
    }
}

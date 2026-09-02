package com.educacion.inedcuchilla.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "maestro")
public class MaestroModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_maestro")
    private Integer idMaestro;

    @Column(name = "codigo_empleado")
    private String codigoEmpleado;

    @OneToOne
    @JoinColumn(name = "fk_id_usuario", nullable = false, unique = true)
    private UsuarioModelo usuario;

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public Integer getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(Integer idMaestro) {
        this.idMaestro = idMaestro;
    }

    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }
}

package com.educacion.inedcuchilla.modelo;

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
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioModelo fkIdUsuario;

    public Integer getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(Integer idMaestro) {
        this.idMaestro = idMaestro;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public UsuarioModelo getFkIdUsuario() {
        return fkIdUsuario;
    }

    public void setFkIdUsuario(UsuarioModelo fkIdUsuario) {
        this.fkIdUsuario = fkIdUsuario;
    }
}

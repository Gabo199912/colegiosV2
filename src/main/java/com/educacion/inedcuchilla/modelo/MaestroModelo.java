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
    private int codigoEmpleado;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioModelo fkIdUsuario;

    public Integer getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(Integer idMaestro) {
        this.idMaestro = idMaestro;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public UsuarioModelo getFkIdUsuario() {
        return fkIdUsuario;
    }

    public void setFkIdUsuario(UsuarioModelo fkIdUsuario) {
        this.fkIdUsuario = fkIdUsuario;
    }
}

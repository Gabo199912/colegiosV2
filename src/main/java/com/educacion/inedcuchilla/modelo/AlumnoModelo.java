package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "alumno")
public class AlumnoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private Integer idUsuario;

    @Column(name = "genero")
    private String genero;

    @Column(name = "codigo_alumno")
    private String codigoAlumno;

    @Column(name = "seccion")
    private String seccion;

    @OneToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioModelo usuario;

    @Column(name = "activo")
    private boolean activo;


    public AlumnoModelo() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

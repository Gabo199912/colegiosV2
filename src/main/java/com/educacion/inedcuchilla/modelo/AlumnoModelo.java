package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

@Entity(name = "alumno")
public class AlumnoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private Integer idAlumno;

    @Column(name = "codigo_alumno")
    private String codigoAlumno;

    @Column(name = "genero")
    private String genero;

    @Column(name = "activo")
    private boolean activo;

    @OneToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioModelo usuario;

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }
}

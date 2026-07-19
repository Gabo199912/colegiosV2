package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "alumno")
public class AlumnoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private Integer idAlumno;

    @Column(name = "codigo_alumno")
    private String codigoAlumno;


    private String genero;
    private Boolean activo;

    @OneToOne
    @JoinColumn(name = "fk_id_usuario", nullable = false, unique = true)
    private UsuarioModelo usuario;

    @OneToMany(mappedBy = "alumno")
    private List<InscripcionModelo> inscripciones;


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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }

    public List<InscripcionModelo> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionModelo> inscripciones) {
        this.inscripciones = inscripciones;
    }


}

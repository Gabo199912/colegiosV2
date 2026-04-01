package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "alumno_encargado")
public class AlumnoEncargadoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlumnoEncargado;

    @Column(name = "parentesco")
    private String parentesco;

    @Column(name = "principal")
    private Boolean principal;

    @ManyToOne
    @JoinColumn(name = "fk_id_alumno", nullable = false)
    private AlumnoModelo alumno;

    @ManyToOne
    @JoinColumn(name = "fk_id_encargado", nullable = false)
    private EncargadoModelo encargado;


    public AlumnoEncargadoModelo() {
    }


    public int getIdAlumnoEncargado() {
        return idAlumnoEncargado;
    }

    public void setIdAlumnoEncargado(int idAlumnoEncargado) {
        this.idAlumnoEncargado = idAlumnoEncargado;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public AlumnoModelo getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoModelo alumno) {
        this.alumno = alumno;
    }

    public EncargadoModelo getEncargado() {
        return encargado;
    }

    public void setEncargado(EncargadoModelo encargado) {
        this.encargado = encargado;
    }
}

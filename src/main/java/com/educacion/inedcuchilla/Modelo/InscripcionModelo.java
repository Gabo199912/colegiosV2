package com.educacion.inedcuchilla.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "inscripcion")
public class InscripcionModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripcion")
    private Integer idInscripcion;

    @Column(name = "inscripcion_activa")
    private Boolean inscripcionActiva;

    @ManyToOne
    @JoinColumn(name = "fk_id_grado_academico")
    private GradoAcademicoModelo gradoAcademico;

    @ManyToOne
    @JoinColumn(name = "fk_id_alumno")
    private AlumnoModelo alumno;

    @ManyToOne
    @JoinColumn(name = "fk_id_ciclo_escolar")
    private CicloEscolarModelo cicloEscolar;

    public Integer getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(Integer idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Boolean getInscripcionActiva() {
        return inscripcionActiva;
    }

    public void setInscripcionActiva(Boolean inscripcionActiva) {
        this.inscripcionActiva = inscripcionActiva;
    }

    public GradoAcademicoModelo getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(GradoAcademicoModelo gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public CicloEscolarModelo getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(CicloEscolarModelo cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    public AlumnoModelo getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoModelo alumno) {
        this.alumno = alumno;
    }
}

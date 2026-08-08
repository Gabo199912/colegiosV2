package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "grado_academico")
public class GradoAcademicoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado_academico")
    private Integer idGradoAcademico;

    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "fk_id_especialidad")
    private EspecialidadModelo especialidad;

    @ManyToOne
    @JoinColumn(name = "fk_id_seccion")
    private SeccionModelo seccion;

    @ManyToOne
    @JoinColumn(name = "fk_id_grado")
    private GradoModelo grado;

    @OneToMany(mappedBy = "gradoAcademico")
    private List<InscripcionModelo> inscripciones;

    @OneToMany(mappedBy = "gradoAcademico", cascade = CascadeType.ALL)
    private List<MateriaModelo> materias;

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public SeccionModelo getSeccion() {
        return seccion;
    }

    public void setSeccion(SeccionModelo seccion) {
        this.seccion = seccion;
    }

    public Integer getIdGradoAcademico() {
        return idGradoAcademico;
    }

    public void setIdGradoAcademico(Integer idGradoAcademico) {
        this.idGradoAcademico = idGradoAcademico;
    }

    public EspecialidadModelo getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadModelo especialidad) {
        this.especialidad = especialidad;
    }

    public GradoModelo getGrado() {
        return grado;
    }

    public void setGrado(GradoModelo grado) {
        this.grado = grado;
    }

    public List<InscripcionModelo> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionModelo> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public List<MateriaModelo> getMaterias() {
        return materias;
    }

    public void setMaterias(List<MateriaModelo> materias) {
        this.materias = materias;
    }
}

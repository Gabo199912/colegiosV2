package com.educacion.inedcuchilla.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "grado_academico_materia")
public class GradoAcademicoMateriaModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado_academico_materia")
    private Integer idGradoAcademicoMateria;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_id_grado_academico")
    private GradoAcademicoModelo gradoAcademico;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_id_materia")
    private MateriaModelo materia;

    public Integer getIdGradoAcademicoMateria() {
        return idGradoAcademicoMateria;
    }

    public void setIdGradoAcademicoMateria(Integer idGradoAcademicoMateria) {
        this.idGradoAcademicoMateria = idGradoAcademicoMateria;
    }

    public GradoAcademicoModelo getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(GradoAcademicoModelo gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public MateriaModelo getMateria() {
        return materia;
    }

    public void setMateria(MateriaModelo materia) {
        this.materia = materia;
    }


}

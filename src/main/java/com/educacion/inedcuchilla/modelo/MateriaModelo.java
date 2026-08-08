package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "materia")
public class MateriaModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Integer idMateria;

    @Column(name = "nombre_materia")
    private String nombreMateria;

    @ManyToOne
    @JoinColumn(name = "fk_id_grado_academico")
    private GradoAcademicoModelo gradoAcademico;

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public GradoAcademicoModelo getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(GradoAcademicoModelo gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    
}

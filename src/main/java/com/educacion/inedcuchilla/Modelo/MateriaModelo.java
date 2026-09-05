package com.educacion.inedcuchilla.Modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "materia")
public class MateriaModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Integer idMateria;

    @Column(name = "nombre_materia")
    private String nombreMateria;

    @OneToMany(mappedBy = "materia")
    private List<GradoAcademicoMateriaModelo> gradoAcademicoMateria;

    @OneToMany(mappedBy = "materia")
    private List<MaestroMateriaModelo> maestroMateria;


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

    public List<GradoAcademicoMateriaModelo> getGradoAcademicoMateria() {
        return gradoAcademicoMateria;
    }

    public void setGradoAcademicoMateria(List<GradoAcademicoMateriaModelo> gradoAcademicoMateria) {
        this.gradoAcademicoMateria = gradoAcademicoMateria;
    }

    public List<MaestroMateriaModelo> getMaestroMateria() {
        return maestroMateria;
    }

    public void setMaestroMateria(List<MaestroMateriaModelo> maestroMateria) {
        this.maestroMateria = maestroMateria;
    }
}

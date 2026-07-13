package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "especialidad")
public class EspecialidadModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
    private Integer idEspecialidad;

    @Column(name = "nombre_especialidad")
    private String nombreEspecialidad;

    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL)
    private List<GradoAcademicoModelo> gradosAcademicos;

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public List<GradoAcademicoModelo> getGradosAcademicos() {
        return gradosAcademicos;
    }

    public void setGradosAcademicos(List<GradoAcademicoModelo> gradosAcademicos) {
        this.gradosAcademicos = gradosAcademicos;
    }
}

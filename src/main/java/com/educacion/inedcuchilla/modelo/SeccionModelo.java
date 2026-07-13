package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "seccion")
public class SeccionModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seccion")
    private Integer idSeccion;

    private String seccion;

    @OneToMany(mappedBy = "seccion", cascade = CascadeType.ALL)
    private List<GradoAcademicoModelo> gradosAcademicos;

    public Integer getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public List<GradoAcademicoModelo> getGradosAcademicos() {
        return gradosAcademicos;
    }

    public void setGradosAcademicos(List<GradoAcademicoModelo> gradosAcademicos) {
        this.gradosAcademicos = gradosAcademicos;
    }
}

package com.educacion.inedcuchilla.Modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "grado")
public class GradoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado")
    private Integer idGrado;

    private String grado;

    @OneToMany(mappedBy = "grado")
    private List<GradoModelo> grados;

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public List<GradoModelo> getGrados() {
        return grados;
    }

    public void setGrados(List<GradoModelo> grados) {
        this.grados = grados;
    }
}


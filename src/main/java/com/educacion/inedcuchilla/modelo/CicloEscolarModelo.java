package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ciclo_escolar")
public class CicloEscolarModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciclo_escolar")
    private Integer idCicloEscolar;

    private Integer anio;
    private Boolean activo;

    @OneToMany(mappedBy = "cicloEscolar")
    private List<InscripcionModelo> inscripciones;

    public Integer getIdCicloEscolar() {
        return idCicloEscolar;
    }

    public void setIdCicloEscolar(Integer idCicloEscolar) {
        this.idCicloEscolar = idCicloEscolar;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<InscripcionModelo> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionModelo> inscripciones) {
        this.inscripciones = inscripciones;
    }
}

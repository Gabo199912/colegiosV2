package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bimestre")
public class BimestreModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bimestre")
    private Integer idBimestre;

    @Column(name = "nombre_bimestre")
    private String nombreBimestre;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_finalizacion")
    private LocalDate fechaFin;

    @Column(name = "activo")
    private Boolean activo;

    public BimestreModelo() {
    }

    public Integer getIdBimestre() {
        return idBimestre;
    }

    public void setIdBimestre(Integer idBimestre) {
        this.idBimestre = idBimestre;
    }

    public String getNombreBimestre() {
        return nombreBimestre;
    }

    public void setNombreBimestre(String nombreBimestre) {
        this.nombreBimestre = nombreBimestre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

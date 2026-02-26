package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "grado")
public class GradoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado")
    private Integer idGrado;

    @Column(name = "nombre_grado")
    private String nombreGrado;

    @Column(name = "especialidad")
    private String especialidad;

    @Column(name = "seccion")
    private char seccion;

    @Column(name = "nota")
    private Double nota;


    public GradoModelo() {
    }

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public String getNombreGrado() {
        return nombreGrado;
    }

    public void setNombreGrado(String nombreGrado) {
        this.nombreGrado = nombreGrado;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public char getSeccion() {
        return seccion;
    }

    public void setSeccion(char seccion) {
        this.seccion = seccion;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}

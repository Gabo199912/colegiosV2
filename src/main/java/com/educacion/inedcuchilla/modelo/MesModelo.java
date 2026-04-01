package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "mes")
public class MesModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMes;

    @Column(name = "nombre_mes")
    private String nombre;


    public Integer getIdMes() {
        return idMes;
    }

    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

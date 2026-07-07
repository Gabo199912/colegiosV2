package com.educacion.inedcuchilla.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "detalle_mes")
public class DetalleMesModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_mes")
    private Integer idDetalleMes;

    @Column(name = "nombre_mes")
    private String nombreMes;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "activo")
    private Boolean activo;

    @JsonIgnore
    @OneToMany(mappedBy = "detalle")
    List<DetallePagoMesModelo> meses;

    public Integer getIdDetalleMes() {
        return idDetalleMes;
    }

    public void setIdDetalleMes(Integer idDetalleMes) {
        this.idDetalleMes = idDetalleMes;
    }

    public String getNombreMes() {
        return nombreMes;
    }

    public void setNombreMes(String nombreMes) {
        this.nombreMes = nombreMes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
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

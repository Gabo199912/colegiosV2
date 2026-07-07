package com.educacion.inedcuchilla.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "concepto_pago")
public class ConceptoPagoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concepto_pago")
    private Integer idConceptoPago;

    @Column(name = "nombre_concepto")
    private String nombreConcepto;

    @Column(name = "pago_vigente")
    private boolean pagoVigente;

    @JsonIgnore
    @OneToMany(mappedBy = "concepto")
    private List<DetallePagoModelo> detalles;

    public Integer getIdConceptoPago() {
        return idConceptoPago;
    }

    public void setIdConceptoPago(Integer idConceptoPago) {
        this.idConceptoPago = idConceptoPago;
    }

    public String getNombreConcepto() {
        return nombreConcepto;
    }

    public void setNombreConcepto(String nombreConcepto) {
        this.nombreConcepto = nombreConcepto;
    }

    public boolean isPagoVigente() {
        return pagoVigente;
    }

    public void setPagoVigente(boolean pagoVigente) {
        this.pagoVigente = pagoVigente;
    }
}

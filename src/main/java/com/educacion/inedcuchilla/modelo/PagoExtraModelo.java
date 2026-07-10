package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pago_extra")
public class PagoExtraModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_extra")
    private Integer idPagoExtra;

    @Column(name = "tipo_pago")
    private String tipoPago;

    @Column(name = "pago_vigente")
    private Boolean pagoVigente;

    @OneToOne(mappedBy = "pagoExtra")
    private DetallePagoModelo detalles;


    public Integer getIdPagoExtra() {
        return idPagoExtra;
    }

    public void setIdPagoExtra(Integer idPagoExtra) {
        this.idPagoExtra = idPagoExtra;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Boolean getPagoVigente() {
        return pagoVigente;
    }

    public void setPagoVigente(Boolean pagoVigente) {
        this.pagoVigente = pagoVigente;
    }

    public DetallePagoModelo getDetalles() {
        return detalles;
    }

    public void setDetalles(DetallePagoModelo detalles) {
        this.detalles = detalles;
    }
}

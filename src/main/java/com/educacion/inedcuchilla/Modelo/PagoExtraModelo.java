package com.educacion.inedcuchilla.Modelo;

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

    @OneToMany(mappedBy = "pagoExtra")
    private List<DetallePagoModelo> detalles;


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

    public List<DetallePagoModelo> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePagoModelo> detalles) {
        this.detalles = detalles;
    }
}

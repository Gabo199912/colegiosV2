package com.educacion.inedcuchilla.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pagos")
public class PagoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer idPago;

    private boolean activo;

    @Column(name = "tipo_pago")
    private String tipoPago;

    @JsonIgnore
    @OneToMany(mappedBy = "pago")
    private List<DetallePagoModelo> detalles;

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public List<DetallePagoModelo> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePagoModelo> detalles) {
        this.detalles = detalles;
    }
}

package com.educacion.inedcuchilla.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "detalle_pago_mes")
public class DetallePagoMesModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pago_mes")
    private Integer idDetallePagoMes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_id_detalle_pago")
    private DetallePagoModelo detalle;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_id_mes")
    private DetalleMesModelo mes;

    public Integer getIdDetallePagoMes() {
        return idDetallePagoMes;
    }

    public void setIdDetallePagoMes(Integer idDetallePagoMes) {
        this.idDetallePagoMes = idDetallePagoMes;
    }


    public DetallePagoModelo getDetalle() {
        return detalle;
    }

    public void setDetalle(DetallePagoModelo detalle) {
        this.detalle = detalle;
    }

    public DetalleMesModelo getMes() {
        return mes;
    }

    public void setMes(DetalleMesModelo mes) {
        this.mes = mes;
    }
}

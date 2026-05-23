package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "detalle_pago_mes")
public class DetallePagoMesModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pago_mes")
    private Integer idDetallePagoMes;

    @ManyToOne
    @JoinColumn(name = "fk_id_detalle")
    private List<DetallePagoModelo> detallePago;

    @ManyToOne
    @JoinColumn(name = "fk_id_mes")
    private List<DetalleMesModelo> detalleMes;

    public Integer getIdDetallePagoMes() {
        return idDetallePagoMes;
    }

    public void setIdDetallePagoMes(Integer idDetallePagoMes) {
        this.idDetallePagoMes = idDetallePagoMes;
    }

    public List<DetallePagoModelo> getDetallePago() {
        return detallePago;
    }

    public void setDetallePago(List<DetallePagoModelo> detallePago) {
        this.detallePago = detallePago;
    }

    public List<DetalleMesModelo> getDetalleMes() {
        return detalleMes;
    }

    public void setDetalleMes(List<DetalleMesModelo> detalleMes) {
        this.detalleMes = detalleMes;
    }
}

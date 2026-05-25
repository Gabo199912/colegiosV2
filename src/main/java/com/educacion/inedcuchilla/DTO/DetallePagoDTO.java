package com.educacion.inedcuchilla.DTO;

import com.educacion.inedcuchilla.modelo.DetallePagoModelo;

import java.util.List;

public class DetallePagoDTO {
    private DetallePagoModelo detallePago;
    private String nombreUsuario;
    private String tipoPago;
    private List<String> mes;

    public DetallePagoModelo getDetallePago() {
        return detallePago;
    }

    public void setDetallePago(DetallePagoModelo detallePago) {
        this.detallePago = detallePago;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public List<String> getMes() {
        return mes;
    }

    public void setMes(List<String> mes) {
        this.mes = mes;
    }
}

package com.educacion.inedcuchilla.DTO;

import java.math.BigDecimal;

public class DetallePagoRespuestaDTO {
    private String tipoPago;
    private String descripcion;
    private BigDecimal total;
    private String nombre;
    private String seccion;

    public DetallePagoRespuestaDTO(String tipoPago, String descripcion, BigDecimal total, String nombre, String seccion) {
        this.tipoPago = tipoPago;
        this.descripcion = descripcion;
        this.total = total;
        this.nombre = nombre;
        this.seccion = seccion;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
}

package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "detalle_pago")
public class DetallePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pago")
    private int idDetallePago;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;


    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioModelo usuario;

    @ManyToOne
    @JoinColumn(name = "fk_id_pago")
    private PagosModelo pagos;

    public DetallePago() {
    }

    public int getIdDetallePago() {
        return idDetallePago;
    }

    public void setIdDetallePago(int idDetallePago) {
        this.idDetallePago = idDetallePago;
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

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }


    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }

    public PagosModelo getPagos() {
        return pagos;
    }

    public void setPagos(PagosModelo pagos) {
        this.pagos = pagos;
    }
}

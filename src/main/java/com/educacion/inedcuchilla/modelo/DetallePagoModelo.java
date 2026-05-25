package com.educacion.inedcuchilla.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "detalle_pago")
public class DetallePagoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pago")
    private Integer idDetallePago;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_pago")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate fechaPago;

    @Column(name = "pagado")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean pagado;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioModelo usuario;

    @ManyToOne
    @JoinColumn(name = "fk_id_pago")
    private PagosModelo pagos;


    public DetallePagoModelo() {
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


    public void setIdDetallePago(Integer idDetallePago) {
        this.idDetallePago = idDetallePago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
}

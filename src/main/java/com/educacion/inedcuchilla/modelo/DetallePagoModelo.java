package com.educacion.inedcuchilla.modelo;

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

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioModelo usuario;

    @ManyToOne
    @JoinColumn(name = "fk_id_pago")
    private PagosModelo pagos;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @OneToMany
    @JoinColumn(name = "fk_id_mes")
    private List<MesModelo> mes;

    @Column(name = "pagado")
    private boolean pagado;

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

    public List<MesModelo> getMes() {
        return mes;
    }

    public void setMes(List<MesModelo> mes) {
        this.mes = mes;
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

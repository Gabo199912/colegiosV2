package com.educacion.inedcuchilla.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "detalle_pago")
public class DetallePagoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pago")
    private Integer idDetallePago;

    private BigDecimal total;

    private String descripcion;

    private Boolean pagado;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_pago", nullable = false)
    private PagoModelo pago;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_usuario", nullable = false)
    private UsuarioModelo usuario;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_pago_extra", nullable = true)
    private PagoExtraModelo pagoExtra;

    @JsonIgnore
    @OneToMany(mappedBy = "detalle")
    private List<DetallePagoMesModelo> meses;




    public Integer getIdDetallePago() {
        return idDetallePago;
    }

    public void setIdDetallePago(Integer idDetallePago) {
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

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public PagoModelo getPago() {
        return pago;
    }

    public void setPago(PagoModelo pago) {
        this.pago = pago;
    }

    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }

    public List<DetallePagoMesModelo> getMeses() {
        return meses;
    }

    public void setMeses(List<DetallePagoMesModelo> meses) {
        this.meses = meses;
    }

    public PagoExtraModelo getPagoExtra() {
        return pagoExtra;
    }

    public void setPagoExtra(PagoExtraModelo pagoExtra) {
        this.pagoExtra = pagoExtra;
    }
}

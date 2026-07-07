package com.educacion.inedcuchilla.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private BigDecimal total;

    private String descripcion;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    private Boolean pagado;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_pago", nullable = false)
    private PagoModelo pago;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_concepto_pago", nullable = false)
    private ConceptoPagoModelo concepto;

    @JsonIgnore
    @OneToMany(mappedBy = "mes")
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

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public ConceptoPagoModelo getConcepto() {
        return concepto;
    }

    public void setConcepto(ConceptoPagoModelo concepto) {
        this.concepto = concepto;
    }

    public PagoModelo getPago() {
        return pago;
    }

    public void setPago(PagoModelo pago) {
        this.pago = pago;
    }
}

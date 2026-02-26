package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pagos")
public class PagosModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int idPago;

    @Column(name = "mes_pagado")
    private LocalDate mesPagado;


    @Column(name = "tipo_pago")
    private String tipoPago;

    public PagosModelo() {
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public LocalDate getMesPagado() {
        return mesPagado;
    }

    public void setMesPagado(LocalDate mesPagado) {
        this.mesPagado = mesPagado;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
}

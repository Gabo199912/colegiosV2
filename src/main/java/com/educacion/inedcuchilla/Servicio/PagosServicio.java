package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.repositorio.*;
import org.springframework.stereotype.Service;

@Service
public class PagosServicio {
    private final ConceptoPagoRepositorio conceptoPagoRepositorio;
    private final DetalleMesRepositorio detalleMesRepositorio;
    private final DetallePagoRepositorio detallePagoRepositorio;
    private final DetallePagoMesRepositorio detallePagoMesRepositorio;
    private final PagoRepositorio pagoRepositorio;

    public PagosServicio(ConceptoPagoRepositorio conceptoPagoRepositorio,
                         DetalleMesRepositorio detalleMesRepositorio,
                         DetallePagoRepositorio detallePagoRepositorio,
                         DetallePagoMesRepositorio detallePagoMesRepositorio,
                         PagoRepositorio pagoRepositorio) {
        this.conceptoPagoRepositorio = conceptoPagoRepositorio;
        this.detalleMesRepositorio = detalleMesRepositorio;
        this.detallePagoRepositorio = detallePagoRepositorio;
        this.detallePagoMesRepositorio = detallePagoMesRepositorio;
        this.pagoRepositorio = pagoRepositorio;
    }




}

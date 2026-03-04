package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.modelo.PagosModelo;
import com.educacion.inedcuchilla.repositorio.PagosRepositorio;
import org.springframework.stereotype.Service;

@Service
public class PagosServicio {
    private final PagosRepositorio pagosRepositorio;

    public PagosServicio(PagosRepositorio pagosRepositorio) {
        this.pagosRepositorio = pagosRepositorio;
    }

    public PagosModelo guardar(PagosModelo pagosModelo){
        return pagosRepositorio.save(pagosModelo);
    }

    public PagosModelo buscarPorTipoPago(String TipoPago){
        return pagosRepositorio.findByTipoPago(TipoPago);
    }

    public PagosModelo buscarPorIdPago(Integer idPago){
        return pagosRepositorio.findByIdPago(idPago);
    }
}

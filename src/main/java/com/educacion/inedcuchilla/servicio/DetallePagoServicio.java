package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.modelo.DetallePagoModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.repositorio.DetallePagoRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePagoServicio {

    private final DetallePagoRepositorio detallePagoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public DetallePagoServicio(DetallePagoRepositorio detallePagoRepositorio,
                               UsuarioRepositorio usuarioRepositorio) {
        this.detallePagoRepositorio = detallePagoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

}

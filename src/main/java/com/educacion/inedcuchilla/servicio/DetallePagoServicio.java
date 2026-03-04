package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.modelo.DetallePagoModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.repositorio.DetallePagoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePagoServicio {

    private final DetallePagoRepositorio detallePagoRepositorio;

    public DetallePagoServicio(DetallePagoRepositorio detallePagoRepositorio) {
        this.detallePagoRepositorio = detallePagoRepositorio;
    }

    public DetallePagoModelo guardar(DetallePagoModelo detallePagoModelo){
        return detallePagoRepositorio.save(detallePagoModelo);
    }

    public List<DetallePagoModelo> listarDetallePorNombreUsuario(UsuarioModelo usuario) {
        return detallePagoRepositorio.findByUsuario(usuario);
    }

}

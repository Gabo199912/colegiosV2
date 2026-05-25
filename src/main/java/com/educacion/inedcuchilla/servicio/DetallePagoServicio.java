package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.DTO.DetallePagoDTO;
import com.educacion.inedcuchilla.modelo.*;
import com.educacion.inedcuchilla.repositorio.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class DetallePagoServicio {

    private final DetallePagoRepositorio detallePagoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final PagosRepositorio pagosRepositorio;
    private final DetalleMesRepositorio detalleMesRepositorio;
    private final DetallePagoMesRepositorio detallePagoMesRepositorio;

    public DetallePagoServicio(DetallePagoRepositorio detallePagoRepositorio,
                               UsuarioRepositorio usuarioRepositorio,
                               PagosRepositorio pagosRepositorio,
                               DetalleMesRepositorio detalleMesRepositorio,
                               DetallePagoMesRepositorio detallePagoMesRepositorio) {
        this.detallePagoRepositorio = detallePagoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.pagosRepositorio = pagosRepositorio;
        this.detalleMesRepositorio = detalleMesRepositorio;
        this.detallePagoMesRepositorio = detallePagoMesRepositorio;
    }


    @Transactional
    public Map<String, Object> crearDetallePago(DetallePagoDTO detallePago){
        Optional<UsuarioModelo> usuario = usuarioRepositorio.findByNombreUsuario(detallePago.getNombreUsuario());
        List<DetallePagoMesModelo> detallesMes = new ArrayList<>();

        PagosModelo pagos = pagosRepositorio.findByTipoPago(detallePago.getTipoPago());
        boolean existePago = pagosRepositorio.existsByTipoPago(detallePago.getTipoPago());

        if (usuario.isEmpty()){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "el usuario no existe, elija un usuario existente.");
            return respuesta;
        }

        if(!existePago){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "el tipo de pago no existe, elija un pago existente.");
            return respuesta;
        }

        BigDecimal total = BigDecimal.valueOf(detallePago.getMes().size() * 50);
        BigDecimal totalEsperado = detallePago.getDetallePago().getTotal();

        if (total != totalEsperado){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "El total enviado no es exacto con el total de meses.");
            return respuesta;
        }

        DetallePagoModelo detalle = new DetallePagoModelo();
        detalle.setPagos(pagos);
        detalle.setUsuario(usuario.get());
        detalle.setFechaPago(detallePago.getDetallePago().getFechaPago());
        detalle.setTotal(detallePago.getDetallePago().getTotal());
        detalle.setPagado(true);
        DetallePagoModelo detalleGuardado = detallePagoRepositorio.save(detalle);

        for (String meses : detallePago.getMes()){
            DetallePagoMesModelo detallePagoMesModelo = new DetallePagoMesModelo();
            DetalleMesModelo meseModelo = detalleMesRepositorio.findByNombreMes(meses);

            detallePagoMesModelo.setDetallePago(detalleGuardado);
            detallePagoMesModelo.setDetalleMes(meseModelo);
            detallesMes.add(detallePagoMesModelo);
        }


        detallePagoMesRepositorio.saveAll(detallesMes);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "Pago generado correctamente. ");


        return null;

    }

}

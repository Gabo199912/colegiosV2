package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Pagos.PagoExtraDTO;
import com.educacion.inedcuchilla.DTO.Pagos.ResponsePagoExtraDTO;
import com.educacion.inedcuchilla.Modelo.DetallePagoModelo;
import com.educacion.inedcuchilla.Modelo.PagoExtraModelo;
import com.educacion.inedcuchilla.Modelo.PagoModelo;
import com.educacion.inedcuchilla.Modelo.UsuarioModelo;
import com.educacion.inedcuchilla.repositorio.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PagoExtraServicio {
    private final PagoExtraRepositorio pagoExtraRepositorio;
    private final DetallePagoRepositorio detallePagoRepositorio;
    private final PagoRepositorio pagoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;


    public PagoExtraServicio(PagoExtraRepositorio pagoExtraRepositorio,
                             DetallePagoRepositorio detallePagoRepositorio,
                             PagoRepositorio pagoRepositorio,
                             UsuarioRepositorio usuarioRepositorio){
        this.pagoExtraRepositorio = pagoExtraRepositorio;
        this.detallePagoRepositorio = detallePagoRepositorio;
        this.pagoRepositorio = pagoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public ResponseEntity<?> agregarPagoExtra(PagoExtraDTO pagoExtra){
        Map<String, Object> respuesta = new HashMap<>();

        if (!usuarioRepositorio.existsById(pagoExtra.idUsuario())){
            respuesta.put("MENSAJE", "El usuario ingresado no existe.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        if (!pagoRepositorio.existsById(pagoExtra.idPago())){
            respuesta.put("MENSAJE", "el metodo de pago ingresado no existe, favor crealo e intenta de nuevo");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        if (!pagoExtraRepositorio.existsById(pagoExtra.idPagoExtra())){
            respuesta.put("MENSAJE", "El tipo de pago que desea pagar no existe, favor crealo e intenta de nuevo. ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        DetallePagoModelo detalle = new DetallePagoModelo();

        UsuarioModelo usuario = usuarioRepositorio.findByIdUsuario(pagoExtra.idUsuario());
        PagoExtraModelo tipoPagoExtra = pagoExtraRepositorio.findByIdPagoExtra(pagoExtra.idPagoExtra());
        PagoModelo metodoPago = pagoRepositorio.findByIdPago(pagoExtra.idPago());

        detalle.setTotal(pagoExtra.total());
        detalle.setDescripcion(pagoExtra.descripcion());
        detalle.setPagado(true);
        detalle.setUsuario(usuario);
        detalle.setPago(metodoPago);
        detalle.setPagoExtra(tipoPagoExtra);

        DetallePagoModelo pagoGuardado = detallePagoRepositorio.save(detalle);

        ResponsePagoExtraDTO response = new ResponsePagoExtraDTO(
                pagoGuardado.getUsuario().getNombreUsuario(),
                pagoGuardado.getPago().getTipoPago(),
                pagoGuardado.getPagoExtra().getTipoPago(),
                pagoGuardado.getDescripcion(),
                pagoGuardado.getTotal()
        );

        respuesta.put("MENSAJE", "El pago se guardo correctamente.");
        respuesta.put("PAGO", response);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }



}

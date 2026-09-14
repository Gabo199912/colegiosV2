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

import java.util.*;

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

        if(!usuarioRepositorio.existsByNombreUsuario(pagoExtra.nombreUsuario()) && !usuarioRepositorio.existsByEmail(pagoExtra.correoUsuario())){
            respuesta.put("MENSAJE", "El nombre de usuario o el email no existe.");
            respuesta.put("COMO_PROCEDER", "Ingrese un nombre existente por favor.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        if (!pagoExtraRepositorio.existsByTipoPago(pagoExtra.pagoExtra())){
            respuesta.put("MENSAJE", "El pago extra agregado no existe.");
            respuesta.put("COMO_PROCEDER", "Crea el pago o elije uno que si exista.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        if (!pagoRepositorio.existsByTipoPago(pagoExtra.tipoPago())){
            respuesta.put("MENSAJE", "El metodo de pago no existe.");
            respuesta.put("COMO_PROCEDER", "Elija uno existente o cree un metodo nuevo.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        Optional<UsuarioModelo> usuario = usuarioRepositorio.findByNombreUsuario(pagoExtra.nombreUsuario());
        PagoExtraModelo pagoExtraBuscado = pagoExtraRepositorio.findByTipoPago(pagoExtra.pagoExtra());
        Optional<PagoModelo> pagoBuscado = pagoRepositorio.findByTipoPago(pagoExtra.tipoPago());

        DetallePagoModelo detalleNuevo = new DetallePagoModelo();
        detalleNuevo.setTotal(pagoExtra.total());
        detalleNuevo.setDescripcion(pagoExtra.descripcion());
        detalleNuevo.setPagado(true);
        detalleNuevo.setUsuario(usuario.get());
        detalleNuevo.setPagoExtra(pagoExtraBuscado);
        detalleNuevo.setPago(pagoBuscado.get());

        DetallePagoModelo detalleGuardado = detallePagoRepositorio.save(detalleNuevo);

        ResponsePagoExtraDTO detalle = new ResponsePagoExtraDTO(
            usuario.get().getNombreUsuario(),
            pagoExtraBuscado.getTipoPago(),
                pagoBuscado.get().getTipoPago(),
                detalleGuardado.getDescripcion(),
                detalleGuardado.getTotal()
        );

        respuesta.put("MENSAJE", "Pago: " + detalleGuardado.getPagoExtra().getTipoPago() + " agregado correctamente.");
        respuesta.put("PAGO_EXTRA", detalle);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }



}

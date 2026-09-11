package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Meses.MesesDTO;
import com.educacion.inedcuchilla.DTO.Pagos.PagoDTO;
import com.educacion.inedcuchilla.DTO.Pagos.PagoRespuestaDTO;
import com.educacion.inedcuchilla.DTO.Pagos.ValidacionMesesPagadosDTO;
import com.educacion.inedcuchilla.Modelo.*;
import com.educacion.inedcuchilla.repositorio.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PagosServicio {
    private final DetalleMesRepositorio detalleMesRepositorio;
    private final DetallePagoRepositorio detallePagoRepositorio;
    private final DetallePagoMesRepositorio detallePagoMesRepositorio;
    private final PagoRepositorio pagoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final PagoServicioJDBC pagoServicioJDBC;

    public PagosServicio(DetalleMesRepositorio detalleMesRepositorio,
                         DetallePagoRepositorio detallePagoRepositorio,
                         DetallePagoMesRepositorio detallePagoMesRepositorio,
                         PagoRepositorio pagoRepositorio, UsuarioRepositorio usuarioRepositorio,
                         PagoServicioJDBC pagoServicioJDBC) {
        this.detalleMesRepositorio = detalleMesRepositorio;
        this.detallePagoRepositorio = detallePagoRepositorio;
        this.detallePagoMesRepositorio = detallePagoMesRepositorio;
        this.pagoRepositorio = pagoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.pagoServicioJDBC = pagoServicioJDBC;
    }


    //buscar los datos
    // validar que el usuario no duplique pagos,
    //
    @Transactional
    public ResponseEntity<?> pagarMeses(PagoDTO pago){
        Map<String, Object> respuesta = new HashMap<>();


        if (usuarioRepositorio.existsByNombreUsuario(pago.nombreUsuario())){
            respuesta.put("MENSAJE", "el usuario que intentas crear como alumno ya existe.");
            respuesta.put("COMO_PROCEDER", "puedes utilizar la opcion de crear usuario para continuar." );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        if (usuarioRepositorio.existsByEmail(pago.correoUsuario())){
            respuesta.put("MENSAJE", "el correo que intentas crear como alumno ya existe.");
            respuesta.put("COMO_PROCEDER", "puedes utilizar la opcion de crear usuario para continuar." );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        if (pagoRepositorio.existsByTipoPago(pago.tipoDePago())){
            respuesta.put("MENSAJE", "El pago seleccionado no existe.");
            respuesta.put("COMO_PROCEDER", "Crea este tipo de pago o selecciona uno ya existente.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        List<DetalleMesModelo> meses = new ArrayList<>();
        int cantidadMeses = 0;

        for (MesesDTO mes : pago.meses()){
            if (detalleMesRepositorio.existsByIdMes(mes.getIdMes())){
                Optional<DetalleMesModelo> mesEncontrado = detalleMesRepositorio.findById(mes.getIdMes());
                meses.add(mesEncontrado.get());
                cantidadMeses++;
            }
        }

        BigDecimal subTotal = BigDecimal.valueOf(meses.size() * 50.00);

        if (!subTotal.equals(pago.total())){
            respuesta.put("MENSAJE", "El total no cuadra con los meses ingresados");
            respuesta.put("COMO_PROCEDER", "El total debe cuadrar con los meses ingresados, cada mes vale 50");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

}

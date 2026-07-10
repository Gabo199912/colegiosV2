package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.PagoDTO;
import com.educacion.inedcuchilla.DTO.PagoRespuestaDTO;
import com.educacion.inedcuchilla.DTO.ValidacionMesesPagadosDTO;
import com.educacion.inedcuchilla.modelo.*;
import com.educacion.inedcuchilla.repositorio.*;
import org.apache.catalina.connector.Response;
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


    @Transactional
    public ResponseEntity<?> pagarMeses(PagoDTO pago){
        Map<String, Object> respuesta = new HashMap<>();
        Set<Integer> mesesUnicos = new HashSet<>(pago.meses());

        if (mesesUnicos.size() != pago.meses().size()){
            respuesta.put("MENSAJE", "No pueden ir meses repetidos para el pago.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        if (!usuarioRepositorio.existsById(pago.idUsuario())){
            respuesta.put("MENSAJE", "el usuario ingresado no existe.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        if (!pagoRepositorio.existsById(pago.idPago())){
            respuesta.put("MENSAJE", "el pago ingresado no existe.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        List<ValidacionMesesPagadosDTO> validacionMesesPagados = pagoServicioJDBC.mesesPagados(pago.idUsuario());
        List<DetalleMesModelo> meses = detalleMesRepositorio.findAllById(pago.meses());
        List<DetallePagoMesModelo> detallesPagoMes = new ArrayList<>();

        BigDecimal totalValido =
                BigDecimal.valueOf(meses.size())
                        .multiply(BigDecimal.valueOf(50));

        if (pago.total().compareTo(totalValido) != 0) {
            respuesta.put("MENSAJE", "el total ingresado no cuadra con el total de meses a pagar.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }


        for (ValidacionMesesPagadosDTO validacionMesesPagado : validacionMesesPagados){
            for (Integer idMes : pago.meses()){
                if (validacionMesesPagado.idMes().equals(idMes)){
                    respuesta.put("MENSAJE", "el mes " + validacionMesesPagado.nombreMes() + " ya se pago.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
                }
            }
        }


        DetallePagoModelo detallePago = new DetallePagoModelo();

        UsuarioModelo usuario = usuarioRepositorio.findByIdUsuario(pago.idUsuario());
        PagoModelo tipoPago = pagoRepositorio.findByIdPago(pago.idPago());

        detallePago.setTotal(pago.total());
        detallePago.setDescripcion(pago.descripcion());
        detallePago.setPagado(true);
        detallePago.setUsuario(usuario);
        detallePago.setPago(tipoPago);

        DetallePagoModelo detalleGuardado = detallePagoRepositorio.save(detallePago);
        List<String> mesesPagados = new ArrayList<>();

        for (DetalleMesModelo mes : meses){
                DetallePagoMesModelo detallePagoMesModelo = new DetallePagoMesModelo();
                detallePagoMesModelo.setMes(mes);
                detallePagoMesModelo.setDetalle(detalleGuardado);
                detallesPagoMes.add(detallePagoMesModelo);
                mesesPagados.add(mes.getNombreMes());
        }

        detallePagoMesRepositorio.saveAll(detallesPagoMes);
        PagoRespuestaDTO response = new PagoRespuestaDTO(
                usuario.getNombreUsuario(),
                tipoPago.getTipo_pago(),
                mesesPagados,
                pago.total(),
                pago.descripcion()
        );

        respuesta.put("MENSAJE", "pago guardado correctamente");
        respuesta.put("PAGO GUARDADO", response);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}

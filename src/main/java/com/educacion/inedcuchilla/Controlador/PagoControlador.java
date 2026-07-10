package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.DTO.Pagos.PagoDTO;
import com.educacion.inedcuchilla.DTO.Pagos.PagoExtraDTO;
import com.educacion.inedcuchilla.Servicio.PagoExtraServicio;
import com.educacion.inedcuchilla.Servicio.PagosServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pago")
public class PagoControlador {
    private final PagosServicio pagosServicio;
    private final PagoExtraServicio pagoExtraServicio;

    public PagoControlador(PagosServicio pagosServicio,
                           PagoExtraServicio pagoExtraServicio){
        this.pagosServicio = pagosServicio;
        this.pagoExtraServicio = pagoExtraServicio;
    }

    @PostMapping("/pagar-meses")
    public ResponseEntity<?> crearPago(@RequestBody PagoDTO pago){
        Map<String, Object> respuesta = new HashMap<>();

        if (pago.meses().size() < 1){
            respuesta.put("MENSAJE", "Debe elejir almenos un mes para poder agregar un pago");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        if (pago.idUsuario().toString().isEmpty()){
            respuesta.put("MENSAJE", "Debe ingresar un usuario.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        if (pago.idPago().toString().isEmpty()){
            respuesta.put("MENSAJE", "El metodo de pago no puede estar vacío.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        return pagosServicio.pagarMeses(pago);

    }


    @PostMapping("/pago-extra")
    public ResponseEntity<?> crearPagoExtra(@RequestBody PagoExtraDTO pagoExtra){
        Map<String, Object> respuesta = new HashMap<>();

        if (pagoExtra.idPagoExtra().toString().isEmpty()){
            respuesta.put("MENSAJE", "Este pago no se puede realizar, ingresse un pago.");
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }

        if (pagoExtra.idUsuario().toString().isEmpty()){
            respuesta.put("MENSAJE", "El usuario no puede ir vacío.");
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }

        if (pagoExtra.idPago().toString().isEmpty()){
            respuesta.put("MENSAJE", "Elija un metodo de pago que exista o cree uno nuevo.");
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }

        if (pagoExtra.total().compareTo(BigDecimal.ZERO) <= 0) {
            respuesta.put("MENSAJE", "El total a ingresar no puede ser 0 o menor.");
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }

        return pagoExtraServicio.agregarPagoExtra(pagoExtra);
    }
}

package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.DTO.PagoDTO;
import com.educacion.inedcuchilla.Servicio.PagosServicio;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pago")
public class PagoControlador {
    private final PagosServicio pagosServicio;

    public PagoControlador(PagosServicio pagosServicio){
        this.pagosServicio = pagosServicio;
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

}

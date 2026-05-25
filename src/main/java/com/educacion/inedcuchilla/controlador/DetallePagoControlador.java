package com.educacion.inedcuchilla.controlador;


import com.educacion.inedcuchilla.DTO.DetallePagoDTO;
import com.educacion.inedcuchilla.servicio.DetallePagoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/detalle-pago")
public class DetallePagoControlador {
    private final DetallePagoServicio detallePagoServicio;

    public DetallePagoControlador(DetallePagoServicio detallePagoServicio){
        this.detallePagoServicio = detallePagoServicio;
    }

    @PostMapping("/pagar")
    public ResponseEntity<?> crearPago(@RequestBody DetallePagoDTO detallePago){
        if (detallePago.getMes().isEmpty() || detallePago.getDetallePago() == null){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "todos los campos deben ir llenos.");
        }

        Map<String, Object> respuesta =  detallePagoServicio.crearDetallePago(detallePago);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}

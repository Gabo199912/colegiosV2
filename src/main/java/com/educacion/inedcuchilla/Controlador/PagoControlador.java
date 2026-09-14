package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.DTO.Pagos.PagoDTO;
import com.educacion.inedcuchilla.DTO.Pagos.PagoExtraDTO;
import com.educacion.inedcuchilla.Servicio.PagoExtraServicio;
import com.educacion.inedcuchilla.Servicio.PagosServicio;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> crearPago(@Valid @RequestBody PagoDTO pago){
        return pagosServicio.pagarMeses(pago);

    }


    @PostMapping("/pago-extra")
    public ResponseEntity<?> crearPagoExtra(@Valid @RequestBody PagoExtraDTO pagoExtra){
        return pagoExtraServicio.agregarPagoExtra(pagoExtra);
    }
}

package com.educacion.inedcuchilla.controlador;

import com.educacion.inedcuchilla.DTO.DetallePagoRespuestaDTO;
import com.educacion.inedcuchilla.modelo.DetallePagoModelo;
import com.educacion.inedcuchilla.modelo.PagosModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.servicio.DetallePagoServicio;
import com.educacion.inedcuchilla.servicio.PagosServicio;
import com.educacion.inedcuchilla.servicio.UsuarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/detalle-pago")
public class DetallePagoControlador {

    private static final Logger logger = LoggerFactory.getLogger(DetallePagoControlador.class);

    private final DetallePagoServicio detallePagoServicio;
    private final PagosServicio pagosServicio;
    private final UsuarioServicio usuarioServicio;

    public DetallePagoControlador(DetallePagoServicio detallePagoServicio, PagosServicio pagosServicio, UsuarioServicio usuarioServicio) {
        this.detallePagoServicio = detallePagoServicio;
        this.pagosServicio = pagosServicio;
        this.usuarioServicio = usuarioServicio;
    }


    @GetMapping("/listarPorUsuario/{nombre}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable String nombre){
        UsuarioModelo usuario = usuarioServicio.buscarUsuarioPorNombre(nombre);

        if (usuario == null){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "El usuario no existe");
            respuesta.put("usuario", "ingrese un usuario valido");

            return ResponseEntity.status(404).body(respuesta);
        }

        List<DetallePagoModelo> detallePagos = detallePagoServicio.listarDetallePorNombreUsuario(usuario);

        return ResponseEntity.ok(detallePagos);
    }


    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody DetallePagoModelo detallePagoModelo){

        UsuarioModelo usuario = usuarioServicio.buscarPorIdUsuario(detallePagoModelo.getUsuario().getIdUsuario());
        PagosModelo pago = pagosServicio.buscarPorIdPago(detallePagoModelo.getPagos().getIdPago());


        if (detallePagoModelo.getPagos() == null || detallePagoModelo.getUsuario() == null){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "El usuario no existe");
            respuesta.put("usuario", "ingrese un usuario valido");
            return ResponseEntity.status(404).body(respuesta);
        }

        if ((detallePagoModelo.getPagos().getIdPago() == null) || (detallePagoModelo.getUsuario().getIdUsuario() == null)) {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "El usuario o el pago no existe");
            respuesta.put("usuario", "ingrese un usuario valido");
            return ResponseEntity.status(404).body(respuesta);
        }



        detallePagoModelo.setUsuario(usuario);
        detallePagoModelo.setPagos(pago);

        detallePagoServicio.guardar(detallePagoModelo);

        DetallePagoRespuestaDTO respuesta = new DetallePagoRespuestaDTO(
                detallePagoModelo.getPagos().getTipoPago(),
                detallePagoModelo.getDescripcion(),
                detallePagoModelo.getTotal(),
                detallePagoModelo.getUsuario().getNombre(),
                detallePagoModelo.getUsuario().getSeccion()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

    }

}

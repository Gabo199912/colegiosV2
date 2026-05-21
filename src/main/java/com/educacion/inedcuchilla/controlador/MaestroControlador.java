package com.educacion.inedcuchilla.controlador;

import com.educacion.inedcuchilla.DTO.MaestroDTO;
import com.educacion.inedcuchilla.DTO.MaestroJdbcDTO;
import com.educacion.inedcuchilla.modelo.MaestroModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.servicio.MaestroServicio;
import com.educacion.inedcuchilla.servicio.MaestroServicioJDBC;
import com.educacion.inedcuchilla.servicio.UsuarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/maestros")
public class MaestroControlador {
    public final MaestroServicio maestroServicio;
    public final UsuarioServicio usuarioServicio;
    public final MaestroServicioJDBC maestroservicioJDBC;

    public MaestroControlador(MaestroServicio maestroServicio, UsuarioServicio usuarioServicio, MaestroServicioJDBC maestroservicioJDBC){
        this.maestroServicio = maestroServicio;
        this.usuarioServicio = usuarioServicio;
        this.maestroservicioJDBC = maestroservicioJDBC;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarMaestros(){
        List<MaestroModelo> listaMaestros = maestroServicio.listarMaestros();

        if(listaMaestros.isEmpty()){
            String error = "debe crear minimo un usuario que sea maestro.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError(HttpStatus.NOT_FOUND, error));
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaMaestros);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearMaestro(@RequestBody MaestroDTO maestro){
        if (maestro == null){
            String error = "complete todos los campos.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError(HttpStatus.BAD_REQUEST, error));
        }
        Map<String, Object> respuesta = maestroServicio.crearUsuarioMaestro(maestro);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @PostMapping("/asignar-maestro")
    public ResponseEntity<?> agregarRolMaestro(@RequestBody MaestroDTO maestro){
        Optional<UsuarioModelo> usuario  = usuarioServicio.buscarPorNombreUsuario(maestro.getUsuario().getNombreUsuario());

        if (usuario.isEmpty()){
            String error = "complete todos los campos.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError(HttpStatus.BAD_REQUEST, error));
        }

        Map<String, Object> respuesta = maestroServicio.asignarMaestroUsuario(maestro);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody MaestroModelo maestro){
        if (maestro == null){
            String error = "debe de llenar todo correctamente.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError(HttpStatus.BAD_REQUEST, error));
        }

        maestroServicio.crearMaestro(maestro);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "Maestro actualizado correctamente.");
        respuesta.put("Status", maestro);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @PostMapping("/buscar-por-nombre/{nombreMaestro}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombreMaestro){
        if (nombreMaestro.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError( HttpStatus.BAD_REQUEST, "el nombre no debe ir vacío"));
        }

        List<MaestroJdbcDTO> listaMaestros = maestroservicioJDBC.obtenerMaestrosPornombre(nombreMaestro);
        if (listaMaestros.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError(HttpStatus.NOT_FOUND, "no se encontraron maestros con ese nombre"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaMaestros);
    }

    public static Map<String, Object> responseError(HttpStatus status, String error){

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", error);
        respuesta.put("Status", status);

        return respuesta;
    }

}



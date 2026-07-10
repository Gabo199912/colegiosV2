package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.DTO.CrearRolDTO;
import com.educacion.inedcuchilla.DTO.DesactivarRolDTO;
import com.educacion.inedcuchilla.DTO.ListarRolesDTO;
import com.educacion.inedcuchilla.DTO.ListarUsuarioDTO;
import com.educacion.inedcuchilla.Servicio.RolServicio;
import com.educacion.inedcuchilla.modelo.RolModelo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roles")
public class RolesControlador {
    private final RolServicio rolServicio;

    public RolesControlador(RolServicio rolServicio){
        this.rolServicio = rolServicio;
    }


    @GetMapping("/listar")
    public ResponseEntity<?> listarRoles() {
        Map<String, Object> respuesta = new HashMap<>();
        List<ListarRolesDTO> roles = rolServicio.listarRoles();

        if (roles.isEmpty()){
            respuesta.put("MENSAJE", "no se encontraron roles para listar");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        respuesta.put("MENSAJE", "los roles encontrados son: ");
        respuesta.put("ROLES", roles);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @PostMapping("/crear-rol")
    public ResponseEntity<?> crearRol(@RequestBody CrearRolDTO rol){
        Map<String, Object> respuesta = new HashMap<>();
        if (rol.nombreRol().isEmpty()){
            respuesta.put("MENSAJE", "el nombre de rol es obligatorio, escribe un nombre.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        return rolServicio.guardarRol(rol);
    }

    @PatchMapping("/desactivar")
    public ResponseEntity<?> desactivarRol(@RequestBody DesactivarRolDTO rolDesactivar){
        Map<String, Object> respuesta = new HashMap<>();
        if (rolDesactivar.idRol().toString().isEmpty()){
            respuesta.put("MENSAJE", "envíe un id de usuario que exista");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        if (rolDesactivar.nombreRol().isEmpty()){
            respuesta.put("MENSAJE", "El nombre del rol es un campo obligatorio para esta acción.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        return rolServicio.desactivarRol(rolDesactivar);
    }
}

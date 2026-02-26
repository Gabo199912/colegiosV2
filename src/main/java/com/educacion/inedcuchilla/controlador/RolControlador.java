package com.educacion.inedcuchilla.controlador;

import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.servicio.RolServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roles")
public class RolControlador {

    private final RolServicio rolServicio;

    public RolControlador(RolServicio rolServicio) {
        this.rolServicio = rolServicio;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarRoles(){
        List<RolModelo> roles = rolServicio.listarRoles();

        if (roles.isEmpty()){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "No hay roles registrados");
            return ResponseEntity.status(404).body(respuesta);
        }

        return ResponseEntity.ok(rolServicio.listarRoles());
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRol(@RequestBody RolModelo rol){
        boolean existe = rolServicio.existeRolPorNombre(rol.getTipoUsuario());

        if (existe){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "El rol ya existe");
            return ResponseEntity.status(409).body(respuesta);
        }

        rol.setTipoUsuario(rol.getTipoUsuario().toUpperCase());

        return ResponseEntity.ok(rolServicio.guardarRol(rol));
    }

}

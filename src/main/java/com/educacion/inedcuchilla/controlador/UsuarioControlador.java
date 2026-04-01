package com.educacion.inedcuchilla.controlador;


import com.educacion.inedcuchilla.SeguridadConfig.SecurityConfig;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.servicio.RolServicio;
import com.educacion.inedcuchilla.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;
    private final RolServicio rolServicio;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UsuarioControlador(UsuarioServicio usuarioServicio, RolServicio rolServicio, PasswordEncoder passwordEncoder) {
        this.usuarioServicio = usuarioServicio;
        this.rolServicio = rolServicio;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios(){
        List<UsuarioModelo> usuarios = usuarioServicio.listarUsuarios();

        if (usuarios.isEmpty()){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "No hay usuarios registrados");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioModelo usuarioModelo){
        String nombre = usuarioModelo.getNombre();
        boolean existeEmail = usuarioServicio.existeUsuarioPorEmail(usuarioModelo.getEmail());
        String contrasenia = usuarioModelo.getContrasenia();

        if (usuarioServicio.existeUsuarioPorNombre(nombre)){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "El usuario ya existe");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }

        if (existeEmail){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "email incorrecto");
            respuesta.put("email", "ingrese otro email");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }

        if (contrasenia.length() < 7){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "la contraseña es muy corta");
            respuesta.put("contrasenia", "debe ser mayor a 7 caracteres");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }

        usuarioModelo.setContrasenia(passwordEncoder.encode(contrasenia));

        return ResponseEntity.ok(usuarioServicio.guardarUsuario(usuarioModelo));
    }


    @PutMapping("/modificar")
    public ResponseEntity<?> modificarUsuario(@RequestBody UsuarioModelo usuarioModelo){
        return ResponseEntity.ok(usuarioServicio.guardarUsuario(usuarioModelo));
    }

}

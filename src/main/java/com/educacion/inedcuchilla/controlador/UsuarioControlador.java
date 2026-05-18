package com.educacion.inedcuchilla.controlador;


import com.educacion.inedcuchilla.DTO.ListaUsuarioDTO;
import com.educacion.inedcuchilla.DTO.UsuarioAlumnoDTO;
import com.educacion.inedcuchilla.DTO.UsuarioDTO;
import com.educacion.inedcuchilla.DTO.UsuariosConRolDTO;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.servicio.AlumnoServicio;
import com.educacion.inedcuchilla.servicio.UsuarioServicio;
import com.educacion.inedcuchilla.servicio.UsuarioServicioJDBC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;
    private final AlumnoServicio alumnoServicio;
    private final UsuarioServicioJDBC usuarioServicioJDBC;

    public UsuarioControlador(UsuarioServicio usuarioServicio, AlumnoServicio alumnoServicio, UsuarioServicioJDBC usuarioServicioJDBC) {
        this.usuarioServicio = usuarioServicio;
        this.alumnoServicio = alumnoServicio;
        this.usuarioServicioJDBC = usuarioServicioJDBC;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios(){
        List<ListaUsuarioDTO> listaUsuarios = usuarioServicioJDBC.obtenerUsuarios();
        if (listaUsuarios == null){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "NO HAY USUARIOS CREADOS");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaUsuarios);
    }

    @GetMapping("/listar-por-rol/{tipoRol}")
    public ResponseEntity<?> listarPorRol(@PathVariable String tipoRol){
        List<UsuariosConRolDTO> usuarios = usuarioServicioJDBC.obtenerUsuarioConRol(tipoRol.toUpperCase().trim());
        if (usuarios == null){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "NO HAY USUARIOS CREADOS");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/buscar/{nombreUsuario}")
    public ResponseEntity<?> buscarUsuarioPorNombre(@PathVariable String nombreUsuario){
        Optional<UsuarioModelo> usuario = usuarioServicio.buscarPorNombreUsuario(nombreUsuario);
        if (usuario.isEmpty()){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "El usuario no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDTO usuarioDTO){
        try {
            UsuarioModelo usuario = usuarioServicio.guardarUsuario(usuarioDTO);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            System.out.println(usuarioDTO.getUsuario().getNombreUsuario());
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", e.getMessage());
            respuesta.put("STATUS", HttpStatus.CONFLICT);
            respuesta.put("ERROR", e.getCause());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }
    }

    @PostMapping("/desactivar/{nombreUsuario}")// falta terminar.
    public ResponseEntity<?> desactivarUsuario(@PathVariable String nombreUsuario){

        if (nombreUsuario.isEmpty()){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "El usuario no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        Optional<UsuarioModelo> usuario = usuarioServicio.buscarPorNombreUsuario(nombreUsuario);
        usuario.get().setActivo(false);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Usuario desactivado");
        respuesta.put("STATUS", HttpStatus.OK);
        return ResponseEntity.ok(respuesta);

    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioServicio.guardarUsuario(usuarioDTO));
    }






}

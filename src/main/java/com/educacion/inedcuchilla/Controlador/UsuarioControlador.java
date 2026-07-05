package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.DTO.*;
import com.educacion.inedcuchilla.Servicio.RolServicio;
import com.educacion.inedcuchilla.Servicio.UsuarioServicio;
import com.educacion.inedcuchilla.Servicio.UsuarioServicioJDBC;
import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.modelo.UsuarioRolModelo;
import com.educacion.inedcuchilla.repositorio.UsuarioRolRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
    private final UsuarioServicioJDBC usuarioServicioJDBC;
    private final UsuarioServicio usuarioServicio;
    private final RolServicio rolServicio;
    private final UsuarioRolRepositorio usuarioRolRepositorio;

    public UsuarioControlador(UsuarioServicioJDBC usuarioServicioJDBC,
                              UsuarioServicio usuarioServicio,
                              RolServicio rolServicio,
                              UsuarioRolRepositorio usuarioRolRepositorio) {
        this.usuarioServicioJDBC = usuarioServicioJDBC;
        this.usuarioServicio = usuarioServicio;
        this.rolServicio = rolServicio;
        this.usuarioRolRepositorio = usuarioRolRepositorio;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios() {
        List<ListarUsuarioDTO> usuariosListados = usuarioServicioJDBC.listarUsuarios();
        if (usuariosListados.isEmpty()) {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "No existen usuarios, cree uno para listar.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuariosListados);
    }

    @GetMapping("/listar-por-rol/{nombreRol}")
    public ResponseEntity<?> buscarPorRol(@PathVariable String nombreRol) {
        Map<String, Object> respuesta = new HashMap<>();
        if (nombreRol.isEmpty()) {
            respuesta.put("MENSAJE", "el nombre de rol esta vacío");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        List<UsuarioConRolRecordDTO> usuarios = usuarioServicioJDBC.listarPorRol(nombreRol);

        if (usuarios.isEmpty()) {
            respuesta.put("MENSAJE", "No existe ningun usuario con ese rol");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        respuesta.put("MENSAJE", "Usuarios encontrados");
        respuesta.put("USUARIOS", usuarios);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioRecordDTO usuario) {
        try {
            Map<String, Object> respuesta = usuarioServicio.guardarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @PatchMapping("/desactivar/{nombreUsuario}")
    public ResponseEntity<Map<String, Object>> desactivarusuario(@PathVariable String nombreUsuario) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            if (nombreUsuario.isEmpty()) {
                respuesta.put("MENSAJE", "El nombre de usuario no puede ir vacío");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }

            String mensaje = usuarioServicio.desactivarUsuario(nombreUsuario);
            respuesta.put("MENSAJE", mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);

        } catch (NoSuchElementException e) {
            respuesta.put("MENSAJE", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @PostMapping("/crear/usuario-con-alumno")
    public ResponseEntity<?> crearUsuarioConAlumno(@RequestBody AlumnoUsuarioDTO alumnoUsuario){
        Map<String, Object> respuesta = new HashMap<>();
        if (alumnoUsuario.nombreUsuario().isEmpty() || alumnoUsuario.nombre().isEmpty() ||
        alumnoUsuario.apellido().isEmpty() ||
        alumnoUsuario.email().isEmpty() ||
        alumnoUsuario.telefono().isEmpty() ||
        alumnoUsuario.fechaNacimiento().toString().isEmpty() ||
        alumnoUsuario.contrasenia().isEmpty() ||
        alumnoUsuario.genero().isEmpty()){
            respuesta.put("MENSAJE", "complete todos los campos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }


        return usuarioServicio.guardarUsuarioConAlumno(alumnoUsuario);

    }

//    @PatchMapping("/asignar/roles") continuar despues
//    public ResponseEntity<?> asignarRoles(@RequestBody AsignarRoles usuariosConRol){
//        Map<String, Object> respuesta = new HashMap<>();
//
//        if (usuariosConRol.nombreUsuario().isEmpty()){
//            respuesta.put("MENSAJE", "Ingrese un nombre de usuario");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
//        }
//
//        if (usuariosConRol.idRoles().isEmpty()){
//            respuesta.put("MENSAJE", "Ingrese roles para asignar al usuario");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
//        }
//
//        if (!usuarioServicio.buscarPorNombreUsuario(usuariosConRol.nombreUsuario())){
//            respuesta.put("MENSAJE", "el usuario ingresado no exisge");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
//        }
//
//        List<RolModelo> roles = rolServicio.listarRolesPorId(usuariosConRol.idRoles());
//        respuesta = usuarioServicio.agregarRoles(roles, usuariosConRol);
//
//
//        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
//    }



}

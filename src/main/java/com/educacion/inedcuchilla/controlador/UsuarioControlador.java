package com.educacion.inedcuchilla.controlador;


import com.educacion.inedcuchilla.DTO.UsuarioAlumnoDTO;
import com.educacion.inedcuchilla.DTO.UsuarioDTO;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.servicio.AlumnoServicio;
import com.educacion.inedcuchilla.servicio.UsuarioServicio;
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


    public UsuarioControlador(UsuarioServicio usuarioServicio, AlumnoServicio alumnoServicio) {
        this.usuarioServicio = usuarioServicio;
        this.alumnoServicio = alumnoServicio;
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

    //se crea el usuario y el alumno masivamente.
    @PostMapping("/crear/masivo")
    public ResponseEntity<?> crearMasivamente(@RequestParam("alumnos") MultipartFile archivo){
            try {

                Map<String, Object> respuesta = usuarioServicio.cargarExcel(archivo);
                respuesta.put("mensaje", "Archivo cargado correctamente");
                respuesta.put("STATUS", HttpStatus.OK);
                return ResponseEntity.ok(respuesta);
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    }

    //se crea el usuario y el alumno
    @PostMapping("/crear/usuario-con-alumno")
    public ResponseEntity<?> crearUsuarioConAlumno(@RequestBody UsuarioAlumnoDTO usuarioAlumno){
        try {
             usuarioServicio.guardarUsuarioAlumno(usuarioAlumno);
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Alumno y usuario cargado correctamente");
            respuesta.put("STATUS", HttpStatus.OK);
            return ResponseEntity.ok(respuesta);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}

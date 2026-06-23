package com.educacion.inedcuchilla.Controlador;

import com.educacion.inedcuchilla.DTO.ListarUsuarioDTO;
import com.educacion.inedcuchilla.DTO.UsuarioRecordDTO;
import com.educacion.inedcuchilla.Servicio.UsuarioServicio;
import com.educacion.inedcuchilla.Servicio.UsuarioServicioJDBC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("usuarios")
public class UsuarioControlador {
    private final UsuarioServicioJDBC usuarioServicioJDBC;
    private final UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicioJDBC usuarioServicioJDBC,
                              UsuarioServicio usuarioServicio){
        this.usuarioServicioJDBC = usuarioServicioJDBC;
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios(){
        List<ListarUsuarioDTO> usuariosListados = usuarioServicioJDBC.listarUsuarios();
        if (usuariosListados.isEmpty()){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "No existen usuarios, cree uno para listar.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuariosListados);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioRecordDTO usuario){
        try {
            Map<String, Object> respuesta = usuarioServicio.guardarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @PatchMapping("/desactivar")
    public ResponseEntity<Map<String, Object>> desactivarusuario(@RequestBody String nombreUsuario){
        Map<String, Object> respuesta = new HashMap<>();
      try {
          if (nombreUsuario.isEmpty()){
              respuesta.put("MENSAJE", "El nombre de usuario no puede ir vacío");
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
          }

          String mensaje = usuarioServicio.desactivarUsuario(nombreUsuario);
          respuesta.put("MENSAJE", mensaje);
          return ResponseEntity.status(HttpStatus.OK).body(respuesta);

      }catch (NoSuchElementException e){
          respuesta.put("MENSAJE", e.getMessage());
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
      }
    }




}

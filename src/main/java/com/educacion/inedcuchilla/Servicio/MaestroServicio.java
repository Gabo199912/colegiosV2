package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Maestros.MaestroRequest;
import com.educacion.inedcuchilla.DTO.Maestros.MaestroResponse;
import com.educacion.inedcuchilla.modelo.MaestroModelo;
import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.modelo.UsuarioRolModelo;
import com.educacion.inedcuchilla.repositorio.MaestroRepositorio;
import com.educacion.inedcuchilla.repositorio.RolRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRolRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MaestroServicio {
    private final MaestroRepositorio maestroRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final RolRepositorio rolRepositorio;
    private final UsuarioRolRepositorio usuarioRolRepositorio;


    public MaestroServicio(MaestroRepositorio maestroRepositorio,
                           UsuarioRepositorio usuarioRepositorio,
                           PasswordEncoder passwordEncoder,
                           RolRepositorio rolRepositorio, UsuarioRolRepositorio usuarioRolRepositorio){
        this.maestroRepositorio = maestroRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.rolRepositorio = rolRepositorio;
        this.usuarioRolRepositorio = usuarioRolRepositorio;
    }


    public ResponseEntity<Map<String, Object>> crearMaestro(MaestroRequest maestroRequest){
        Map<String, Object> respuesta = new HashMap<>();
        RolModelo rol = rolRepositorio.findByIdRol(3);
        UsuarioRolModelo usuarioRol = new UsuarioRolModelo();
        if (usuarioRepositorio.existsByNombreUsuario(maestroRequest.nombreUsuario())){
            respuesta.put("MENSAJE", "el nombre de usuario ya existe elija otro.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        if (usuarioRepositorio.existsByEmail(maestroRequest.nombreUsuario())){
            respuesta.put("MENSAJE", "El email ya esta registrado elija otro.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }


        UsuarioModelo usuarioNuevo = new UsuarioModelo();
        usuarioNuevo.setNombreUsuario(maestroRequest.nombreUsuario());
        usuarioNuevo.setNombre(maestroRequest.nombre());
        usuarioNuevo.setApellido(maestroRequest.apellido());
        usuarioNuevo.setEmail(maestroRequest.email());
        usuarioNuevo.setTelefono(maestroRequest.telefono());
        usuarioNuevo.setActivo(true);
        usuarioNuevo.setFechaNacimiento(maestroRequest.fechaNacimiento());
        usuarioNuevo.setContrasenia(maestroRequest.contrasenia());

        MaestroModelo maestroNuevo = new MaestroModelo();
        maestroNuevo.setUsuario(usuarioNuevo);
        maestroNuevo.setCodigoEmpleado(maestroNuevo.getCodigoEmpleado());

        usuarioNuevo.setMaestro(maestroNuevo);
        UsuarioModelo usuarioGuardado = usuarioRepositorio.save(usuarioNuevo);

        usuarioRol.setRoles(rol);
        usuarioRol.setUsuario(usuarioGuardado);

        usuarioRolRepositorio.save(usuarioRol);

        MaestroResponse maestroRespuesta = new MaestroResponse(
                usuarioGuardado.getNombreUsuario(),
                usuarioGuardado.getNombre(),
                maestroNuevo.getCodigoEmpleado(),
                usuarioGuardado.getTelefono(),
                usuarioGuardado.getEmail()
                );

        respuesta.put("MENSAJE", "Maestro creado correctamente.");
        respuesta.put("MAESTRO", maestroRespuesta);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}

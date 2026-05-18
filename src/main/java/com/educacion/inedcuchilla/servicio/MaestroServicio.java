package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.DTO.MaestroDTO;
import com.educacion.inedcuchilla.modelo.MaestroModelo;
import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.modelo.UsuarioRolModelo;
import com.educacion.inedcuchilla.repositorio.MaestroRepositorio;
import com.educacion.inedcuchilla.repositorio.RolRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaestroServicio {
    private final MaestroRepositorio maestroRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final RolRepositorio rolRepositorio;

    public  MaestroServicio(MaestroRepositorio maestroRepositorio,
                            UsuarioRepositorio usuarioRepositorio,
                            RolRepositorio rolRepositorio){
        this.maestroRepositorio = maestroRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.rolRepositorio = rolRepositorio;
    }

    public Map<String, Object> crearMaestro(@NonNull MaestroModelo maestroModelo){
        if (maestroModelo == null){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "todos los datos deben de llenarse ");
            return respuesta;
        }

        maestroRepositorio.save(maestroModelo);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "MAESTRO AGREGADO CORRECTAMENTE.");
        respuesta.put("MAESTRO: ", maestroModelo);
        return  respuesta;
    }

    public List<MaestroModelo> listarMaestros(){
        List<MaestroModelo> maestros = maestroRepositorio.findAll();
        return maestros;
    }

    @Transactional
    public Map<String, Object> crearUsuarioMaestro(@NonNull MaestroDTO maestroDTO){
        boolean existeEmail = usuarioRepositorio.existsByEmail(maestroDTO.getUsuario().getEmail());
        boolean existeNombreUsuario = usuarioRepositorio.existsByNombreUsuario(maestroDTO.getUsuario().getNombreUsuario());
        RolModelo rol = rolRepositorio.findByTipoUsuario("MAESTRO");
        UsuarioRolModelo usuarioRol = new UsuarioRolModelo();

        if (existeEmail){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE","el email no debe ir vacio o ya existe");
            respuesta.put("STATUS", HttpStatus.BAD_REQUEST);
            return respuesta;
        }

        if (existeNombreUsuario){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE","el nombre de usuario no debe ir vacio o ya existe");
            respuesta.put("STATUS", HttpStatus.BAD_REQUEST);
            return respuesta;
        }


        UsuarioModelo usuarioGuardado = usuarioRepositorio.save(maestroDTO.getUsuario());
        usuarioRol.setRoles(rol);
        usuarioRol.setUsuario(usuarioGuardado);
        maestroDTO.getMaestro().setFkIdUsuario(usuarioGuardado);
        maestroRepositorio.save(maestroDTO.getMaestro());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "MAESTRO CREADO CORRECTAMENTE.");
        respuesta.put("STATUS", HttpStatus.OK);
        return respuesta;

    }

    @Transactional
    public Map<String, Object> asignarMaestroUsuario(@NonNull MaestroDTO maestroDTO){
        RolModelo rol = rolRepositorio.findByTipoUsuario("MAESTRO");
        UsuarioRolModelo usuarioRol = new UsuarioRolModelo();



        usuarioRol.setRoles(rol);
        usuarioRol.setUsuario(maestroDTO.getUsuario());
        maestroDTO.getMaestro().setFkIdUsuario(maestroDTO.getUsuario());
        maestroRepositorio.save(maestroDTO.getMaestro());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "ROL MAESTRO ASIGNADO A ." + maestroDTO.getUsuario().getNombre() + " Correctamente.");
        respuesta.put("STATUS", HttpStatus.OK);
        return respuesta;

    }



    public static Map<String, Object> responseError(HttpStatus status){

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "Maestro actualizado correctamente.");
        respuesta.put("Status", status);

        return respuesta;
    }

}

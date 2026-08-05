package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Usuarios.UsuarioDTO;
import com.educacion.inedcuchilla.DTO.Usuarios.UsuarioRecordDTO;
import com.educacion.inedcuchilla.DTO.Usuarios.UsuarioResponseDTO;
import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.modelo.UsuarioRolModelo;
import com.educacion.inedcuchilla.repositorio.RolRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRolRepositorio;
import org.jspecify.annotations.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final RolRepositorio rolRepositorio;
    private final UsuarioRolRepositorio usuarioRolRepositorio;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio,
                           PasswordEncoder passwordEncoder,
                           RolRepositorio rolRepositorio,
                           UsuarioRolRepositorio usuarioRolRepositorio){
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.rolRepositorio = rolRepositorio;
        this.usuarioRolRepositorio = usuarioRolRepositorio;
    }



    @Transactional
    public Map<String, Object> guardarUsuario(UsuarioRecordDTO usuario){
        Map<String, Object> respuesta = new HashMap<>();
        Integer omitidos = 0;

        if (usuarioRepositorio.existsByNombreUsuario(usuario.nombreUsuario())){
            respuesta.put("MENSAJE", "el usuario ya existe.");
            return respuesta;
        }

        if (usuarioRepositorio.existsByEmail(usuario.email())){
            respuesta.put("MENSAJE", "el email ya existe, ingrese uno diferente.");
            return respuesta;
        }

        if (usuario.contrasenia().length() < 7){
            respuesta.put("MENSAJE", "la contraseña dbe tener minimo 7 caracteres");
            return respuesta;
        }

        UsuarioModelo usuarioNuevo = new UsuarioModelo();
        usuarioNuevo.setContrasenia(passwordEncoder.encode(usuario.contrasenia()));
        usuarioNuevo.setNombreUsuario(usuario.nombreUsuario());
        usuarioNuevo.setNombre(usuario.nombre());
        usuarioNuevo.setApellido(usuario.apellido());
        usuarioNuevo.setEmail(usuario.email());
        usuarioNuevo.setTelefono(usuario.telefono());
        usuarioNuevo.setFechaNacimiento(usuario.fechaNacimiento());
        usuarioNuevo.setActivo(true);

        UsuarioModelo usuarioGuardado = usuarioRepositorio.save(usuarioNuevo);

        /*funciona pero necesita mejora para que sea mas eficiente*/
        for (Integer rol : usuario.idRoles()){
            if (rolRepositorio.existsByIdRol(rol)){
                UsuarioRolModelo usuarioRolModelo = new UsuarioRolModelo();
                RolModelo rolModelo = rolRepositorio.findByIdRol(rol);
                usuarioRolModelo.setUsuario(usuarioGuardado);
                usuarioRolModelo.setRoles(rolModelo);
                usuarioRolRepositorio.save(usuarioRolModelo);
            }else {
                omitidos++;
            }
        }

        UsuarioResponseDTO responseUsuario = new UsuarioResponseDTO(
                usuarioGuardado.getNombreUsuario(),
                usuarioGuardado.getNombre(),
                usuarioGuardado.getApellido(),
                usuarioGuardado.getEmail(),
                usuarioGuardado.getTelefono()
        );

        respuesta.put("MENSAJE", "Usuario creado correctamente.");
        respuesta.put("USUARIO", responseUsuario);
        respuesta.put("ROLES ", omitidos + " Roles omitidos.");
        return respuesta;
    }


    @Transactional
    public UsuarioModelo guardarSuperUsuario(@NonNull UsuarioDTO usuarioDTO){
        String contrasenia = usuarioDTO.getUsuario().getContrasenia();

        usuarioDTO.getUsuario().setContrasenia(passwordEncoder.encode(contrasenia));

        UsuarioModelo usuarioGuardado = usuarioRepositorio.save(usuarioDTO.getUsuario());

        for (RolModelo rol: usuarioDTO.getRoles()){
            if (rolRepositorio.existsByIdRol(rol.getIdRol())){
                UsuarioRolModelo usuarioRol = new UsuarioRolModelo();

                usuarioRol.setRoles(rol);
                usuarioRol.setUsuario(usuarioGuardado);
                usuarioRolRepositorio.save(usuarioRol);
            }
        }

        return usuarioGuardado;
    }

    public String desactivarUsuario(String nombreUsuario){
        Optional<UsuarioModelo> usuario = Optional.of(usuarioRepositorio.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new NoSuchElementException("El usario no esxiste.")));

        usuario.get().setActivo(false);
        usuarioRepositorio.save(usuario.get());

        return "Usuario correctamente desactivado.";
    }

    public boolean buscarPorNombreUsuario(String nombreUsuario){
        return usuarioRepositorio.existsByNombreUsuario(nombreUsuario);
    }

    //continuar despues. 
//    public Map<String, Object> agregarRoles(List<RolModelo> roles, AsignarRoles usuarioConRoles) {
//        Set<Integer> idNuevos = new HashSet<>();
//        Optional<UsuarioModelo> usuario = usuarioRepositorio.findByNombreUsuario(usuarioConRoles.nombreUsuario());
//        List<UsuarioRolModelo> usuarioRol = new ArrayList<>();
//
//        for (RolModelo ur: roles){
//            idNuevos.add(ur.getIdRol());
//        }
//
//
//
//        usuarioRolRepositorio.saveAll(usuarioRol);
//
//        return
//    }


}

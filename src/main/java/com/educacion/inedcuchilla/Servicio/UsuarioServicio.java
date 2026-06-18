package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.UsuarioDTO;
import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.modelo.UsuarioRolModelo;
import com.educacion.inedcuchilla.repositorio.RolRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRolRepositorio;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public UsuarioModelo guardarUsuario(@NotNull UsuarioDTO usuarioDTO){

        String nombreUsuario = usuarioDTO.getUsuario().getNombreUsuario();
        boolean existeEmail = existeUsuarioPorEmail(usuarioDTO.getUsuario().getEmail());
        String contrasenia = usuarioDTO.getUsuario().getContrasenia();

        if (existeUsuarioPorNombreUsuario(nombreUsuario)){
            throw new RuntimeException("El usuario ya existe");
        }

        if (existeEmail){
            throw new RuntimeException("El email ya existe");
        }

        if (contrasenia.length() < 7){
            throw new RuntimeException("La contraseña es muy corta");
        }

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


    public boolean existeUsuarioPorEmail(String email){
        boolean existe = usuarioRepositorio.existsByEmail(email);
        return existe;
    }

    public boolean existeUsuarioPorNombreUsuario(String nombreUsuario){
        boolean existe = usuarioRepositorio.existsByNombreUsuario(nombreUsuario);
        return existe;
    }

    public boolean existeUsuarioPorNombre(String nombre){
        boolean existe = usuarioRepositorio.existsByNombreUsuario(nombre);
        return existe;
    }
}

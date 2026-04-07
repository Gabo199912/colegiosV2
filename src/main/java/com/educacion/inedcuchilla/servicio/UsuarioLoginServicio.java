package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioLoginServicio implements UserDetailsService {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {

        Optional<UsuarioModelo> usuarioOptional = usuarioRepositorio.findByNombreUsuario(nombreUsuario);

        if (usuarioOptional.isEmpty()){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        UsuarioModelo usuario = usuarioOptional.orElseThrow();


        //segun la logica de la bd, utilizamos slo uno
        //pero spring siempre espera una lista de grantedAuthority
        String roles = usuario.getRol().getTipoUsuario().trim();

        System.out.println(usuario.getNombreUsuario() + " " + "parte del usuarioLoginServicio");

        return new org.springframework.security.core.userdetails.User(
                usuario.getNombreUsuario().trim(),
                usuario.getContrasenia().trim(),
                usuario.getActivo(),
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority("ROLE_" +  roles.trim())));

    }

}

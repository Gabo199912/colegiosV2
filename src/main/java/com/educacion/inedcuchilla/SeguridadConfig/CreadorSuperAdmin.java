package com.educacion.inedcuchilla.SeguridadConfig;

import com.educacion.inedcuchilla.DTO.UsuarioDTO;
import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.modelo.UsuarioRolModelo;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import com.educacion.inedcuchilla.servicio.RolServicio;
import com.educacion.inedcuchilla.servicio.UsuarioServicio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreadorSuperAdmin implements CommandLineRunner {


    private final UsuarioServicio usuarioServicio;
    private final RolServicio rolServicio;

    public CreadorSuperAdmin(UsuarioServicio usuarioServicio, RolServicio rolServicio){
        this.usuarioServicio = usuarioServicio;
        this.rolServicio = rolServicio;

    }

    @Override
    public void run(String... args) throws Exception {
        Boolean existe = usuarioServicio.existeUsuarioPorNombreUsuario("Gabo");

        if (existe){
            System.out.println("usuario ADMIN ya creado");
            return;
        }

        UsuarioDTO usuario = new UsuarioDTO();
        UsuarioModelo superUsuario = new UsuarioModelo();


        superUsuario.setNombreUsuario("Gabo");
        superUsuario.setNombre("Angel");
        superUsuario.setApellido("vasquez");
        superUsuario.setEmail("angel@example.com");
        superUsuario.setTelefono("31905967");
        superUsuario.setActivo(true);
        superUsuario.setFechaNacimiento(LocalDate.of(1995, 8, 15));
        superUsuario.setContrasenia("1234567");
        List<RolModelo> roles = rolServicio.listarRoles();

        usuario.setRoles(roles);
        usuario.setUsuario(superUsuario);
        usuarioServicio.guardarUsuario(usuario);

        System.out.println("EL USUARIO SUPER ADMIN SE GUARDO CORRECTAMENTE.");

    }
}

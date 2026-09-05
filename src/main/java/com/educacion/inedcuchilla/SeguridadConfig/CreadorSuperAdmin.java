package com.educacion.inedcuchilla.SeguridadConfig;

import com.educacion.inedcuchilla.DTO.Usuarios.UsuarioDTO;
import com.educacion.inedcuchilla.Servicio.RolServicio;
import com.educacion.inedcuchilla.Servicio.UsuarioServicio;
import com.educacion.inedcuchilla.Modelo.RolModelo;
import com.educacion.inedcuchilla.Modelo.UsuarioModelo;
import com.educacion.inedcuchilla.Modelo.UsuarioRolModelo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
        Boolean existe = usuarioServicio.buscarPorNombreUsuario("Gabo");

        if (existe){
            System.out.println("usuario SUPER-ADMIN ya creado");
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
        List<RolModelo> roles = rolServicio.listarRolesParaSuperAdmin();
        UsuarioRolModelo usuarioRolModelo = new UsuarioRolModelo();



        usuario.setRoles(roles);
        usuario.setUsuario(superUsuario);

        UsuarioModelo usuarioGuardado = usuarioServicio.guardarSuperUsuario(usuario);

        System.out.println("Super usuario: " + usuarioGuardado.getNombreUsuario() + " Creado correctamente.");

    }
}

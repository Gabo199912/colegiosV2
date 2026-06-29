package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.CrearRolDTO;
import com.educacion.inedcuchilla.DTO.DesactivarRolDTO;
import com.educacion.inedcuchilla.DTO.ListarRolesDTO;
import com.educacion.inedcuchilla.DTO.ListarUsuarioDTO;
import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.repositorio.RolRepositorio;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RolServicio {
    private final RolRepositorio rolRepositorio;
    private final RolServicioJDBC rolServicioJDBC;

    public RolServicio(RolRepositorio rolRepositorio,
                       RolServicioJDBC rolServicioJDBC){
        this.rolRepositorio = rolRepositorio;
        this.rolServicioJDBC = rolServicioJDBC;
    }

    public List<RolModelo> listarRolesParaSuperAdmin(){
        List<RolModelo> listaRoles = rolRepositorio.findAll();
        return listaRoles;
    }

    public List<ListarRolesDTO> listarRoles(){
        List<ListarRolesDTO> roles = rolServicioJDBC.listarRolesJDBC();
        return roles;
    }

    public List<RolModelo> listarRolesPorId(List<Integer> idRoles){
        List<RolModelo> roles = rolRepositorio.findAllById(idRoles);
        return roles;
    }

    public ResponseEntity<?> guardarRol(CrearRolDTO rol){
        Map<String, Object> respuesta = new HashMap<>();
        boolean existe = rolRepositorio.existsByTipoUsuario(rol.nombreRol());

        if (existe){
            respuesta.put("MENSAJE", "El rol ya existe, ingrese otro nombre para el rol");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        RolModelo nuevoRol = new RolModelo();
        nuevoRol.setTipoUsuario(rol.nombreRol());
        nuevoRol.setDescripcion(rol.descripcion());
        nuevoRol.setEstado(true);

        RolModelo rolGuardado = rolRepositorio.save(nuevoRol);

        respuesta.put("MENSAJE", "El rol se agrego correctamente.");
        respuesta.put("Rol", rolGuardado);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    public ResponseEntity<Map<String, Object>> desactivarRol(DesactivarRolDTO rol){
        Map<String, Object> respuesta = new HashMap<>();
        boolean existePorId = rolRepositorio.existsByIdRol(rol.idRol());
        boolean existePorNombre = rolRepositorio.existsByTipoUsuario(rol.nombreRol());

        if (!existePorId || !existePorNombre){
            respuesta.put("MENSAJE", "el rol ingresado no existe.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        RolModelo rolEncontrado = rolRepositorio.findByIdRol(rol.idRol());
        rolEncontrado.setEstado(false);

        respuesta.put("MENSAJE", "El rol fue desactivado correctamente");
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }
}

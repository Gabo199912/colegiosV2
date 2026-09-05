package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Usuarios.ListarUsuarioDTO;
import com.educacion.inedcuchilla.DTO.Usuarios.UsuarioConRolRecordDTO;
import com.educacion.inedcuchilla.DTO.Roles.RolesPorUsuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicioJDBC {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioServicioJDBC(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ListarUsuarioDTO> listarUsuarios(){
        String sql = "SELECT * FROM usuario";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
           ListarUsuarioDTO listaUsuarios = new ListarUsuarioDTO();
           listaUsuarios.setNombreUsuario(rs.getString("nombre_usuario"));
           listaUsuarios.setNombre(rs.getString("nombre"));
           listaUsuarios.setApellido(rs.getString("apellido"));
           listaUsuarios.setEmail(rs.getString("email"));
           listaUsuarios.setTelefono(rs.getString("telefono"));
           listaUsuarios.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());

           return listaUsuarios;
        });
    }

    public List<UsuarioConRolRecordDTO> listarPorRol(String nombreRol){
        String sql = "select usuario.nombre_usuario, " +
                "usuario.nombre," +
                "usuario.apellido, " +
                "usuario.email, " +
                "usuario.telefono, " +
                "usuario.fecha_nacimiento, " +
                "rol.tipo_usuario " +
                "from usuario INNER JOIN usuario_rol " +
                "ON usuario.id_usuario = usuario_rol.fk_id_usuario " +
                "INNER JOIN rol ON usuario_rol.fk_id_rol = rol.id_rol where rol.tipo_usuario = ?;";



        return jdbcTemplate.query(sql, (rs, rowNum) ->{
            UsuarioConRolRecordDTO usuario = new UsuarioConRolRecordDTO(
                    rs.getString("nombre_usuario"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getDate("fecha_nacimiento").toLocalDate(),
                    rs.getString("tipo_usuario")
            );
            return usuario;
        }, nombreRol.trim().toUpperCase());
    }


    public List<RolesPorUsuario> buscarRolesPorUsuario(String nombreUsuario){
        String sql = "select rol.id_rol, " +
                    "rol.tipo_usuario " +
                    "from usuario " +
                    "INNER JOIN usuario_rol " +
                    "ON usuario.id_usuario = usuario_rol.fk_id_usuario " +
                    "INNER JOIN rol " +
                    "ON usuario_rol.fk_id_rol = rol.id_rol where usuario.nombre_usuario = ?";

        return jdbcTemplate.query(sql, (rs, rowNum)->{
            RolesPorUsuario usuarioRol = new RolesPorUsuario(
                    rs.getInt("id_rol"),
                    rs.getString("tipo_usuario")
            );
            return usuarioRol;
        }, nombreUsuario);
    }

}

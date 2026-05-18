package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.DTO.ListaUsuarioDTO;
import com.educacion.inedcuchilla.DTO.UsuarioDTO;
import com.educacion.inedcuchilla.DTO.UsuariosConRolDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicioJDBC {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioServicioJDBC(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ListaUsuarioDTO> obtenerUsuarios(){
        String sql = "SELECT * FROM usuario";
        return jdbcTemplate.query(sql, (rs,  rowNum) ->{
            ListaUsuarioDTO listaUsuarioDTO = new ListaUsuarioDTO();
            listaUsuarioDTO.setNombreUsuario(rs.getString("nombre_usuario"));
            listaUsuarioDTO.setNombre(rs.getString("nombre"));
            listaUsuarioDTO.setApellido(rs.getString("apellido"));
            listaUsuarioDTO.setEmail(rs.getString("email"));
            listaUsuarioDTO.setTelefono(rs.getString("telefono"));
            listaUsuarioDTO.setActivo(Boolean.valueOf(rs.getString("activo")));
            listaUsuarioDTO.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());

            return listaUsuarioDTO;
        });
    }

    public List<UsuariosConRolDTO> obtenerUsuarioConRol(String tipoRol){
        String sql = "select u.nombre_usuario, u.email, r.tipo_usuario from usuario u " +
                "    INNER JOIN usuario_rol ur ON u.id_usuario = ur.fk_id_usuario " +
                "    INNER JOIN rol r ON ur.fk_id_rol = r.id_rol where r.tipo_usuario = ?;";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UsuariosConRolDTO usuariosConRol = new UsuariosConRolDTO(
                    rs.getString("nombre_usuario"),
                    rs.getString("email"),
                    rs.getString("tipo_usuario")
            );

            return usuariosConRol;
        }, tipoRol);
    }
}

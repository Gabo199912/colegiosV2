package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.ListarUsuarioDTO;
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

}

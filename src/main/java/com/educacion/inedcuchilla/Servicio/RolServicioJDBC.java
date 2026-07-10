package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Roles.ListarRolesDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServicioJDBC {
    private final JdbcTemplate jdbcTemplate;

    public RolServicioJDBC(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ListarRolesDTO> listarRolesJDBC(){
        String sql = "select rol.tipo_usuario, rol.estado, rol.descripcion from rol";

        return jdbcTemplate.query(sql, (rs, rowNum) ->{
            ListarRolesDTO roles = new ListarRolesDTO(
                    rs.getString("tipo_usuario"),
                    rs.getString("descripcion"),
                    rs.getBoolean("estado")
            );

            return roles;
        });
    }
}

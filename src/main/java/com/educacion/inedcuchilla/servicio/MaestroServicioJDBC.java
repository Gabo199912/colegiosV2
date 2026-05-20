package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.DTO.MaestroJdbcDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaestroServicioJDBC {

    private final JdbcTemplate jdbcTemplate;

    public MaestroServicioJDBC(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public String obtenerTipoUsuario(String nombreUsuario){
        String sql = "select r.tipo_usuario from usuario u " +
                "    inner join usuario_rol ur on u.id_usuario = ur.fk_id_usuario " +
                "    inner join rol r on ur.fk_id_rol = r.id_rol where tipo_usuario = 'MAESTRO' and u.nombre_usuario = ?;";

        return jdbcTemplate.queryForObject(
                sql,
                String.class,
                nombreUsuario
        );
    }

    public List<MaestroJdbcDTO> obtenerMaestrosPornombre(String nombreUsuario){
        String sql = "select u.nombre_usuario, u.nombre, u.telefono, u.email, r.tipo_usuario from usuario u" +
                "    INNER JOIN usuario_rol ur ON u.id_usuario = ur.fk_id_usuario" +
                "    INNER JOIN rol r ON ur.fk_id_rol = r.id_rol" +
                "    where r.tipo_usuario = 'MAESTRO' and u.nombre_usuario = CONCAT(?,'%')";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            MaestroJdbcDTO maestro = new MaestroJdbcDTO(
              rs.getString("nombre_usuario"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("tipo_usuario")
            );
            return maestro;
        }, nombreUsuario);
    }

}

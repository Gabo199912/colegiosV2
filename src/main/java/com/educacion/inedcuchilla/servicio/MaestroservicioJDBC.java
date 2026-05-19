package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.modelo.MaestroModelo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaestroservicioJDBC {

    private final JdbcTemplate jdbcTemplate;

    public MaestroservicioJDBC(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public String obtenerTipoUsuario(String nombreUsuario){
        String sql = "select r.tipo_usuario from usuario u " +
                "    inner join usuario_rol ur on u.id_usuario = ur.fk_id_usuario " +
                "    inner join rol r on ur.fk_id_rol = r.id_rol where tipo_usuario = 'MAESTRO' and u.nombre_usuario = ?;";

        return jdbcTemplate.query(sql, (rs, rowNum) ->{
            String tipoUsuario = rs.getArray("tipo_usuario"));
        return tipoUsuario;
        }, nombreUsuario);
    }

}

package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Maestros.MaestroResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaestroServicioJDBC {
    private final JdbcTemplate jdbcTemplate;

    public MaestroServicioJDBC(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<MaestroResponse> listarMaestros(){
        String sql = "select usuario.nombre_usuario as nombreUsuario, " +
                "       usuario.nombre, " +
                "       maestro.codigo_empleado as codigoEmpleado, " +
                "       usuario.telefono, " +
                "       usuario.email from " +
                " usuario inner join maestro " +
                " on usuario.id_usuario = maestro.fk_id_usuario";


        return jdbcTemplate.query(sql, (rs,rowNum) ->{
            MaestroResponse response = new MaestroResponse(
                    rs.getString("nombreUsuario"),
                    rs.getString("nombre"),
                    rs.getString("codigoEmpleado"),
                    rs.getString("telefono"),
                    rs.getString("email")
            );

            return response;
        });
    }
}

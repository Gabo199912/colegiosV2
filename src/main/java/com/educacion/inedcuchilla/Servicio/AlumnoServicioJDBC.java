package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Alumnos.AlumnoListas;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServicioJDBC {
    private final JdbcTemplate jdbcTemplate;

    public AlumnoServicioJDBC(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AlumnoListas> listarAlumnos(){
        String sql = "select " +
                "    u.nombre_usuario as nombreUsuario, " +
                "    u.nombre, " +
                "    u.apellido, " +
                "    u.email, " +
                "    u.telefono, " +
                "    a.codigo_alumno as codigoAlumno " +
                "from alumno a inner join usuario u " +
                "    on a.fk_id_usuario = u.id_usuario " +
                "    inner join usuario_rol ur on u.id_usuario = ur.fk_id_usuario " +
                "    inner join rol r on ur.fk_id_rol = r.id_rol";

        return jdbcTemplate.query(sql, (rs, rowNum) ->{
            AlumnoListas alumnos = new AlumnoListas(
                    rs.getString("nombreUsuario"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("codigoAlumno")
            );
            return alumnos;
        });
    }
}

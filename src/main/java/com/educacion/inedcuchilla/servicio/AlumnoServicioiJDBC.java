package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.DTO.AlumnoJdbcDTO;
import com.educacion.inedcuchilla.DTO.UsuariosConRolDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServicioiJDBC {

    private final JdbcTemplate jdbcTemplate;

    public AlumnoServicioiJDBC(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AlumnoJdbcDTO> obtenerAlumnoConNombre(String nombreUsuario){
        String sql = "SELECT u.nombre, u.apellido, u.telefono, u.email, a.codigo_alumno, g.especialidad, g.nombre_grado, g.seccion FROM usuario u " +
                "INNER JOIN usuario_rol ur ON u.id_usuario = ur.fk_id_usuario " +
                "INNER JOIN rol r ON ur.fk_id_rol = r.id_rol " +
                "INNER JOIN alumno a ON u.id_usuario = a.fk_id_usuario " +
                "INNER JOIN grado g ON a.fk_id_grado = g.id_grado " +
                "WHERE r.tipo_usuario = 'ALUMNO' AND u.nombre LIKE CONCAT(?, '%');";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            AlumnoJdbcDTO alumno = new AlumnoJdbcDTO(
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("codigo_alumno"),
                    rs.getString("especialidad"),
                    rs.getString("nombre_grado"),
                    rs.getString("seccion")
            );
            return alumno;
            }, nombreUsuario);
    }
}

package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Grado.GradoResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServicioJDBC {
    private final JdbcTemplate jdbcTemplate;

    public MateriaServicioJDBC(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<GradoResponse> buscarGrados(String nombreEspecialidad, String grado){
        String sql = "select grado_academico.id_grado_academico as idGradoAcademico, " +
                "   grado.grado, " +
                "   especialidad.nombre_especialidad as nombreEspecialidad, " +
                "       seccion.seccion " +
                "       from grado_academico " +
                "inner join grado " +
                "on grado_academico.fk_id_grado = grado.id_grado " +
                "inner join especialidad " +
                "on grado_academico.fk_id_especialidad = especialidad.id_especialidad " +
                "inner join seccion " +
                "on grado_academico.fk_id_seccion = seccion.id_seccion " +
                "where especialidad.nombre_especialidad = ? and grado.grado = ?";


        return jdbcTemplate.query(sql, (rs, rowNum) ->{
            GradoResponse grados = new GradoResponse(
                    rs.getInt("idGradoAcademico"),
                    rs.getString("grado"),
                    rs.getString("nombreEspecialidad"),
                    rs.getString("seccion")
            );
            return grados;
        }, nombreEspecialidad, grado);
    }
}

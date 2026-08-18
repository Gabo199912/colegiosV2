package com.educacion.inedcuchilla.Servicio;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MateriaServicioJDBC {
    private final JdbcTemplate jdbcTemplate;

    public MateriaServicioJDBC(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public Integer idGradoAcademico(String grado, String nombreEspecialidad){
        String sql = " SELECT " +
                " ga.id_grado_academico, " +
                " from grado_academico ga " +
                "INNER JOIN especialidad e " +
                "ON ga.fk_id_especialidad = e.id_especialidad " +
                "INNER JOIN seccion s " +
                "ON ga.fk_id_seccion = s.id_seccion " +
                "INNER JOIN grado g " +
                "ON ga.fk_id_grado = g.id_grado where grado = ? AND nombre_especialidad = ?";


        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->{
            Integer idGradoAcad = rs.getInt(1);
            return idGradoAcad;
        }, grado, nombreEspecialidad);
    }
}

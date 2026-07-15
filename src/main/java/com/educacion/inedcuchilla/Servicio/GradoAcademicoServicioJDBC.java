package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Grado.GradoAcademicoDTO;
import com.educacion.inedcuchilla.DTO.Grado.GradoAcademicoResponse;
import com.educacion.inedcuchilla.DTO.Grado.GradoDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradoAcademicoServicioJDBC {
    private final JdbcTemplate jdbcTemplate;

    public GradoAcademicoServicioJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<GradoDTO> listarGrados(){
        String sql = "SELECT " +
                "       grado.grado, " +
                "       seccion.seccion, " +
                "       especialidad.nombre_especialidad " +
                "   FROM GRADO_ACADEMICO " +
                "    INNER JOIN grado " +
                "    ON GRADO_ACADEMICO.fk_id_grado = grado.id_grado " +
                "    INNER JOIN especialidad " +
                "    ON GRADO_ACADEMICO.fk_id_especialidad = especialidad.id_especialidad " +
                "    INNER JOIN SECCION " +
                "    ON GRADO_ACADEMICO.fk_id_seccion = SECCION.id_seccion";

        return jdbcTemplate.query(sql, (rs, rowNum)->{
           GradoDTO grados = new GradoDTO(
                   rs.getString("grado"),
                   rs.getString("seccion"),
                   rs.getString("nombre_especialidad")
           );

           return grados;
        });
    }

    public Optional<Integer> buscarIdGradoAcadmico(GradoDTO grado){
        String sql = "select grado_academico.id_grado_academico as idGradoAcademico from grado_academico " +
                "    INNER JOIN grado  ON grado_academico.fk_id_grado = grado.id_grado " +
                "    INNER JOIN especialidad ON grado_academico.fk_id_especialidad = especialidad.id_especialidad " +
                "    INNER JOIN seccion ON grado_academico.fk_id_seccion = seccion.id_seccion " +
                "                                          where grado.grado = ? " +
                "                                            AND seccion.seccion = ? " +
                "                                            AND especialidad.nombre_especialidad = ?";

        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->{
                Integer idGradoAcademico = rs.getInt("idGradoAcademico");

                return Optional.of(idGradoAcademico);
            }, grado.nombreGrado(), grado.nombreSeccion(), grado.nombreEspecialidad());
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

}




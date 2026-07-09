package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.ValidacionMesesPagadosDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServicioJDBC {
    private final JdbcTemplate jdbcTemplate;

    public PagoServicioJDBC(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<ValidacionMesesPagadosDTO> mesesPagados(Integer idUsuario){
        String sql = "select u.nombre_usuario, " +
                "       p.tipo_pago, " +
                "       de.pagado, " +
                "       dm.id_mes, " +
                "       dm.nombre_mes " +
                "from usuario u INNER JOIN detalle_pago de " +
                "                          ON u.id_usuario = de.fk_id_usuario " +
                "               INNER JOIN detalle_pago_mes dpm " +
                "                          ON de.id_detalle_pago = dpm.fk_id_detalle_pago " +
                "               INNER JOIN detalle_mes dm " +
                "                          ON dpm.fk_id_mes = dm.id_mes " +
                "               INNER JOIN pagos p " +
                "                          ON de.fk_id_pago = p.id_pago where u.id_usuario = ?";


        return jdbcTemplate.query(sql, (rs, rowNum) ->{
           ValidacionMesesPagadosDTO validacionMesesPagadosDTO = new ValidacionMesesPagadosDTO(
                   rs.getString("nombre_usuario"),
                   rs.getString("tipo_pago"),
                   rs.getBoolean("pagado"),
                   rs.getInt("id_mes"),
                   rs.getString("nombre_mes")
           );

           return validacionMesesPagadosDTO;
        }, idUsuario);
    }
}

package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Pagos.ValidacionMesesPagadosDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PagoServicioJDBC {
    private final JdbcTemplate jdbcTemplate;

    public PagoServicioJDBC(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<ValidacionMesesPagadosDTO> mesesPagadosV1(Integer idUsuario){
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

    public Set<String> mesesPagados(String nombreUsuario){
        String sql = "select  detalle_mes.nombre_mes " +
                "from usuario " +
                "inner join detalle_pago " +
                "on usuario.id_usuario = detalle_pago.fk_id_usuario " +
                "inner join detalle_pago_mes " +
                "on detalle_pago.id_detalle_pago = detalle_pago_mes.fk_id_detalle_pago " +
                "inner join detalle_mes " +
                "on detalle_pago_mes.fk_id_mes = detalle_mes.id_mes " +
                "where usuario.nombre_usuario = ? ";

        List<String> lista = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getString("nombre_mes"),
                nombreUsuario);
        return new HashSet<>(lista);
    }


}

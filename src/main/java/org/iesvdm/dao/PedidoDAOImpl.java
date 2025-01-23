package org.iesvdm.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.modelo.Pedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class PedidoDAOImpl implements PedidoDAO{
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Pedido> listar(int idComercial) {

        List<Pedido> listPedido = jdbcTemplate.query(
                "SELECT * FROM pedido WHERE id_comercial = ?",
                (rs, rowNum) -> new Pedido(rs.getInt("id"),
                        rs.getDouble("total"),
                        rs.getDate("fecha"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_comercial")), idComercial
        );

        log.info("Devueltos {} registros.", listPedido.size());

        return listPedido;
    }
}

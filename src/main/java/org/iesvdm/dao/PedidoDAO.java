package org.iesvdm.dao;

import org.iesvdm.modelo.Pedido;

import java.util.List;

public interface PedidoDAO {
    public List<Pedido> listar(int idComercial);
}

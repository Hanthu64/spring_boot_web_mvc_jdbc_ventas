package org.iesvdm.service;

import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercialService {
    private ComercialDAO comercialDAO;
    private PedidoDAO pedidoDAO;

    public ComercialService(ComercialDAO comercialDAO) {
        this.comercialDAO = comercialDAO;
    }

    public List<Comercial> listAll(){
        return comercialDAO.getAll();
    }

    public Comercial one(int id) {
        Optional<Comercial> optionalComercial = comercialDAO.find(id);
        if (optionalComercial.isPresent())
            return optionalComercial.get();
        else
            return null;
    }

    public void newComercial(Comercial comercial) {

        comercialDAO.create(comercial);

    }

    public void replaceComercial(Comercial comercial) {

        comercialDAO.update(comercial);

    }

    public void deleteComercial(int id) {

        comercialDAO.delete(id);

    }

    public List<Pedido> listarPedidos(int idComercial){

        return pedidoDAO.listar(idComercial);

    }
}

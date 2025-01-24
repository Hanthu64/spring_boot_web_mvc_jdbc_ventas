package org.iesvdm.controlador;

import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.iesvdm.service.ClienteService;
import org.iesvdm.service.ComercialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
public class ComercialController {
    private final ComercialService comercialService;
    private final ClienteService clienteService;
    public ComercialController(ComercialService comercialService, ClienteService clienteService) {
        this.comercialService = comercialService;
        this.clienteService = clienteService;
    }

    @GetMapping("/comerciales")
    public String listar(Model model){
        List<Comercial> listaComerciales = comercialService.listAll();
        model.addAttribute("listaComerciales", listaComerciales);

        return "comerciales";
    }

    @GetMapping("/comerciales/{id}")
    public String detalle(Model model, @PathVariable int id ) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        List<Pedido> listaPedidos = comercialService.listarPedidos(id);

        Map<Pedido, Cliente> mapaPedidos = new HashMap<>();
        for (Pedido a : listaPedidos){
            Cliente one = clienteService.one(a.getIdCliente());
            mapaPedidos.put(a, one);
        }
        model.addAttribute("MapaPedidos", mapaPedidos);

        return "detalle-comercial";

    }
}

package org.iesvdm.controlador;

import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.service.ComercialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ComercialController {
    private ComercialService comercialService;
    public ComercialController(ComercialService comercialService) {
        this.comercialService = comercialService;
    }

    @GetMapping("/comerciales")
    public String listar(Model model){
        List<Comercial> listaComerciales = comercialService.listAll();
        model.addAttribute("listaComerciales", listaComerciales);

        return "comerciales";
    }

    @GetMapping("/comerciales/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "detalle-comercial";

    }
}

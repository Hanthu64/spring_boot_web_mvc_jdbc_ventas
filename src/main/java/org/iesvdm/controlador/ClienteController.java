package org.iesvdm.controlador;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.iesvdm.dto.ClienteDTO;
import org.iesvdm.mapper.ClienteMapper;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@AllArgsConstructor
//Se puede fijar ruta base de las peticiones de este controlador.
//Los mappings de los métodos tendrían este valor /clientes como
//prefijo.
//@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteService clienteService;
	
	//Se utiliza inyección automática por constructor del framework Spring.
	//Por tanto, se puede omitir la anotación Autowired
	//@Autowired
	//@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	//equivalente a la siguiente anotación
	@GetMapping("/clientes") //Al no tener ruta base para el controlador, cada método tiene que tener la ruta completa
	public String listar(Model model) {
		
		/*List<Cliente> listaClientes =  clienteService.listAll();
		model.addAttribute("listaClientes", listaClientes);*/

		List<ClienteDTO> dtoClientes = clienteService.listAllDTO();
		model.addAttribute("DTOClientes", dtoClientes);

		int pedidoMax = 0;
		int pedidoMin = Integer.MAX_VALUE;
		Iterator i = dtoClientes.iterator();
		while (i.hasNext()) {
			ClienteDTO aux = (ClienteDTO) i.next();
			if(aux.getNumeroTotalPedidos() > pedidoMax){
				pedidoMax = aux.getNumeroTotalPedidos();
			}
			if(aux.getNumeroTotalPedidos() < pedidoMin){
				pedidoMin = aux.getNumeroTotalPedidos();
			}
		}

		model.addAttribute("max", pedidoMax);
		model.addAttribute("min", pedidoMin);

		List<ClienteDTO> dtoSorted = dtoClientes.stream()
				.sorted(Comparator.comparing(ClienteDTO::getNumeroTotalPedidos).reversed())
				.toList();

		model.addAttribute("DTOSorted", dtoSorted);
		return "clientes";
		
	}

	@GetMapping("/clientes/{id}")
	public String detalle(Model model, @PathVariable Integer id ) {

		List<ClienteDTO> dtoClientes = clienteService.listAllDTO();
		ClienteDTO cliente = dtoClientes.stream()
				.filter(c -> c.getId() == id)
				.findFirst()
				.orElse(null);

		model.addAttribute("cliente", cliente);

		return "detalle-cliente";

	}

	@GetMapping("/clientes/crear")
	public String crear(Model model) {

		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);

		return "crear-cliente";

	}

	@PostMapping({"/clientes/crear", "/clientes/crear"})
	public String submitCrear(@Valid @ModelAttribute Cliente cliente, BindingResult bindingResulted, Model model) {
		if(bindingResulted.hasErrors()){
			model.addAttribute("cliente", cliente);
			return "crear-cliente";
		}

		clienteService.newCliente(cliente);
		return "redirect:/clientes";
	}

	@GetMapping("/clientes/editar/{id}")
	public String editar(Model model, @PathVariable Integer id) {

		Cliente cliente = clienteService.one(id);
		model.addAttribute("cliente", cliente);

		return "editar-cliente";

	}


	@PostMapping("/clientes/editar/{id}")
	public RedirectView submitEditar(@ModelAttribute("cliente") Cliente cliente) {

		clienteService.replaceCliente(cliente);

		return new RedirectView("/clientes");
	}

	@PostMapping("/clientes/borrar/{id}")
	public RedirectView submitBorrar(@PathVariable Integer id) {

		clienteService.deleteCliente(id);

		return new RedirectView("/clientes");
	}

	@GetMapping("/validation")
	public String validation(@ModelAttribute Cliente cliente, Model model){
		return "validation";
	}

	@PostMapping("/validation")
	public String validationPost(@Valid @ModelAttribute Cliente cliente, BindingResult bindingResulted, Model model){

		model.addAttribute("cliente", cliente);
		model.addAttribute("toString", cliente.toString());
		return "validation";
	}
}

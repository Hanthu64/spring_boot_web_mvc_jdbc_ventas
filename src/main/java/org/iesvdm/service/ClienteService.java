package org.iesvdm.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.dto.ClienteDTO;
import org.iesvdm.mapper.ClienteMapper;
import org.iesvdm.mapper.ClienteMapperImpl;
import org.iesvdm.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	private ClienteMapper clienteMapper;
	
	//Se utiliza inyección automática por constructor del framework Spring.
	//Por tanto, se puede omitir la anotación Autowired
	//@Autowired
	
	public List<Cliente> listAll() {
		
		return clienteDAO.getAll();
		
	}

	public Cliente one(int id) {
		Optional<Cliente> optionalCliente = clienteDAO.find(id);
		if (optionalCliente.isPresent())
			return optionalCliente.get();
		else
			return null;
	}

	public void newCliente(Cliente cliente) {

		clienteDAO.create(cliente);

	}

	public void replaceCliente(Cliente cliente) {

		clienteDAO.update(cliente);

	}

	public void deleteCliente(int id) {

		clienteDAO.delete(id);

	}

	public List<ClienteDTO> listAllDTO(){
		List<Cliente> listCliente = clienteDAO.getAll();

		Map<Long, Integer> mapNumPedByIdCli = clienteDAO.getNumeroPedidosByIdCliente();

        return listCliente.stream().map(c -> clienteMapper
						.clienteAClienteDTO(c, mapNumPedByIdCli.get(c.getId())))
				.sorted(Comparator.comparing(ClienteDTO::getId))
				.toList();
	}
}

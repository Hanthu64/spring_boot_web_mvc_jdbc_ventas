package org.iesvdm.mapper;

import org.iesvdm.dto.ClienteDTO;
import org.iesvdm.modelo.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDTO clienteAClienteDTO(Cliente cliente, Integer numeroTotalPedidos);
    Cliente clienteDTOaCliente(ClienteDTO clienteDTO);
}

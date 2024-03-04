package br.com.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.server.entities.Cliente;
import br.com.server.entities.ClienteMesa;
import br.com.server.entities.Mesa;
import br.com.server.entities.dto.clientedto.ClienteShowMesaDTO;
import br.com.server.exceptions.ClienteMesaException;
import br.com.server.repositorys.ClienteMesaRepository;
import br.com.server.repositorys.ClienteRepository;
import br.com.server.repositorys.MesaRepository;

@Service
public class ClienteMesaService {

	@Autowired
	private ClienteMesaRepository repository;
	
	@Autowired 
	private ClienteRepository clienteRepository;
	
	@Autowired
	private MesaRepository mesaRepository;
	
	//GetMapping
	public List<ClienteMesa> getAllMesa()
	{
		return repository.findAll();
	}
	
	//PostMapping
	public ClienteMesa getClienteMesaByIdCliente(Long id)
	{
		ClienteMesa cliente0 = repository.findByIdCliente(id);
		if (cliente0 == null)
		{
			throw new ClienteMesaException("Nenhum cliente com id '" + id + "' na mesa.");
		}
		else {
			return cliente0;
		}		
	}
	
	//PostMapping
	public List<ClienteShowMesaDTO> getClienteMesaByIdMesa(Long id)
	{
		List<ClienteMesa> clienteMesa0 = repository.findByIdMesa(id);
		if (clienteMesa0 == null)
		{
			throw new ClienteMesaException("Nenhuma mesa com id '" + id + "' na mesa.");
		}
		else {
			Mesa mesa0 = mesaRepository.findById(id)
					.orElseThrow(() -> new ClienteMesaException("Mesa com id '" + id + "' nao encontrada"));
			
			List<ClienteMesa> listaIdClientes = repository.findIdClienteByIdMesa(mesa0.getIdMesa());
			
			if (listaIdClientes == null)
			{
				throw new ClienteMesaException("Erro ao resgatar ID'S");
			}
			List<Long> idsClientes = new ArrayList<>();
			for (ClienteMesa clienteMesa : listaIdClientes) {
			    idsClientes.add(clienteMesa.getIdCliente());
			}
			
			List<Cliente> listaClientes = clienteRepository.findByIdClienteIn(idsClientes);
			if (listaClientes == null)
			{
				throw new ClienteMesaException("Nenhum cliente encontrado");
			}
			else {
				List<ClienteShowMesaDTO> listaClientesDTO = new ArrayList<>();
				for (Cliente cliente: listaClientes)
				{
					ClienteShowMesaDTO clienteDTO = new ClienteShowMesaDTO(cliente.getNomeCliente(), cliente.getIdCliente());
					listaClientesDTO.add(clienteDTO);
				}
				return listaClientesDTO;
			}
		}		
	}
	
	//PostMapping
	public void addClienteMesa(ClienteMesa clienteMesa)
	{
		if (clienteMesa == null)
		{
			throw new ClienteMesaException("Nao é possivel registrar um cliente em uma mesa com os aspectos nulos.");
		}
		else
		{
			Cliente cliente0 = clienteRepository.findById(clienteMesa.getIdCliente())
					.orElseThrow(() -> new ClienteMesaException("Cliente com id '" + clienteMesa.getIdCliente() + "' nao encontrado."));
			Mesa mesa0 = mesaRepository.findById(clienteMesa.getIdMesa())
					.orElseThrow(() -> new ClienteMesaException("Mesa com id '" + clienteMesa.getIdMesa() + "' nao encontrada"));
			
			ClienteMesa clienteMesa0 = repository.findByIdClienteAndIdMesa(clienteMesa.getIdCliente(), clienteMesa.getIdMesa());
			if (clienteMesa0 == null)
			{				
				repository.saveAndFlush(clienteMesa);
				return;
			}
			else {
				return;
			}
		}
	}

	//Delete mapping
	public ClienteMesa deleteClienteMesa(Long idCliente, Long idMesa)
	{
		Cliente cliente0 = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ClienteMesaException("Cliente com id '" + idCliente + "' nao encontrado."));
		
		Mesa mesa0 = mesaRepository.findById(idMesa)
				.orElseThrow(() -> new ClienteMesaException("Mesa com id '" + idMesa + "' nao encontrada."));
				
			
		if (mesa0 == null)
		{
			throw new ClienteMesaException("Impossível deletar com uma mesa nula.");
		}
		if (cliente0 == null)
		{
			throw new ClienteMesaException("Impossível deletar com um cliente nulo.");
		}
		
		ClienteMesa clienteAndMesa = repository.findByIdClienteAndIdMesa(idCliente, idMesa);
		if (clienteAndMesa == null)
		{
			throw new ClienteMesaException("Cliente/Mesa incorretos, impossível de deletar.");
		}
		else {
			repository.deleteById(clienteAndMesa.getIdClienteMesa());
			return clienteAndMesa;
		}
	}
}

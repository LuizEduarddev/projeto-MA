package br.com.server.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.server.entities.Cliente;
import br.com.server.entities.ClienteMesa;
import br.com.server.entities.Mesa;
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
	public List<Cliente> getClienteMesaByIdMesa(Long id)
	{
		ClienteMesa clienteMesa0 = repository.findByIdMesa(id);
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
				return listaClientes;
			}
		}		
	}
	
	//PostMapping
	public ClienteMesa addClienteMesa(ClienteMesa clienteMesa)
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
			
			repository.saveAndFlush(clienteMesa);
			return clienteMesa;
		}
	}

	//Delete mapping
	public ClienteMesa deleteClienteMesa(ClienteMesa mesaClienteDelete)
	{
		Cliente cliente0 = clienteRepository.findById(mesaClienteDelete.getIdCliente())
				.orElseThrow(() -> new ClienteMesaException("Cliente com id '" + mesaClienteDelete.getIdCliente() + "' nao encontrado."));
		
		Mesa mesa0 = mesaRepository.findById(mesaClienteDelete.getIdMesa())
				.orElseThrow(() -> new ClienteMesaException("Mesa com id '" + mesaClienteDelete.getIdMesa() + "' nao encontrada."));
				
			
		if (mesa0 == null)
		{
			throw new ClienteMesaException("Impossível deletar com uma mesa nula.");
		}
		if (cliente0 == null)
		{
			throw new ClienteMesaException("Impossível deletar com um cliente nulo.");
		}
		
		ClienteMesa clienteAndMesa = repository.findByIdClienteAndIdMesa(mesaClienteDelete.getIdCliente(), mesaClienteDelete.getIdMesa());
		if (clienteAndMesa == null)
		{
			throw new ClienteMesaException("Registro nulo, impossível de deletar.");
		}
		else {
			repository.deleteByIdClienteAndIdMesa(mesaClienteDelete.getIdCliente(), mesaClienteDelete.getIdMesa());
			return clienteAndMesa;
		}
	}
}

package br.com.server.services;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.server.entities.Cliente;
import br.com.server.entities.dto.clientedto.ClienteLoginDTO;
import br.com.server.exceptions.ClienteException;
import br.com.server.repositorys.ClienteRepository;
import jakarta.persistence.EntityManager;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
    private EntityManager manager;
	
	//Get Mapping
	public List<Cliente> getAllCliente()
	{
		return repository.findAll();
	}
	
	//Post Mapping
	public Cliente getClienteById(Long id)
	{
		Cliente cliente = repository.findById(id).
				orElseThrow(() -> 
				new ClienteException("Nenhum cliente com id '" + id + "' encontrado."));
		
		return cliente;
	}
	
	//Post Mapping
	public Cliente addCliente(Cliente cliente)
	{
		if (cliente == null)
		{
			throw new ClienteException("Cliente nao pode ser nulo.");
		}
		else
		{
			repository.saveAndFlush(cliente);
			return cliente;
		}
	}
	
	//Post Mapping
	public ClienteLoginDTO checkCliente(ClienteLoginDTO dto)
	{
		if (dto == null)
		{
			throw new ClienteException("credenciais nao podem ser nulas.");
		}
		else
		{
			if (dto.getCpfDTO() == null)
			{
				throw new ClienteException("Cpf nao pode ser nulo.");
			}
			else
			{
				String cpf = dto.getCpfDTO();	
				Cliente cliente0 = repository.findByCpfCliente(cpf);
				
				if (cliente0 == null)
				{
					throw new ClienteException("cliente com cpf '" + cpf + "' nao cadastrado");
				}
				else
				{					
					ClienteLoginDTO clienteReturned = new ClienteLoginDTO(cliente0);
					if (cliente0.getSenhaCliente().equals(dto.getSenhaDTO()))
					{
						return clienteReturned;
					}
					else
					{
						throw new ClienteException("CPF ou SENHA incorretos, por favor, tente novamente.");
					}
				}
			}
		}
	}
	
	//Put Mapping
	public Cliente alterCliente(Long id, Cliente novoCliente)
	{
		Cliente cliente0 = repository.findById(id)
				.orElseThrow(() -> 
				new ClienteException("Cliente com id '" + id + "' nao encontrado."));
				
		try {
	        Class<?> clienteClass = Cliente.class;
	        Field[] fields = clienteClass.getDeclaredFields();

	        for (Field field : fields) {
	            field.setAccessible(true);
	            Object value = field.get(novoCliente);
	            if (value != null) {
	                field.set(cliente0, value);
	            }
	        }

	        repository.saveAndFlush(cliente0);
	        return novoCliente;
	        
	    } catch (IllegalAccessException e) {
	        throw new ClienteException("Erro ao atualizar cliente." + e);
	    }
	}
	
	//Delete Mapping
	public Cliente deleteCliente(Long id)
	{
		Cliente clienteDelete = repository.findById(id).
				orElseThrow(() -> new ClienteException("Cliente com id '" + id + "' nao encontrado"));
		
		if (clienteDelete != null)
		{
			Cliente clienteSave = clienteDelete;
			repository.delete(clienteDelete);
			return clienteSave;
		}
		else
		{
			throw new ClienteException("Impossivel deletar um cadastro nulo.");
		}
	}
	
}

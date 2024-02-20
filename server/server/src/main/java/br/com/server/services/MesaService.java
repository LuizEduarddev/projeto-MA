package br.com.server.services;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.server.entities.Mesa;
import br.com.server.exceptions.MesaException;
import br.com.server.exceptions.ProdutoException;
import br.com.server.repositorys.MesaRepository;

@Service
public class MesaService 
{
	@Autowired
	private MesaRepository repository;
	
	//GetMapping
	public List<Mesa> getAllMesa()
	{
		return repository.findAll();
	}
	
	//PostMapping
	public Mesa getMesaById(Long id)
	{
		return repository.findById(id)
				.orElseThrow(() -> new 
				MesaException("Nenhuma mesa encontrada com id: " + id));
	}
	
	//PostMapping
	public Mesa addMesa(Mesa mesa)
	{
		if (mesa == null)
		{
			throw new ProdutoException("A mesa nao pode ser nula.");
		}
		else
		{
			repository.saveAndFlush(mesa);
			return mesa;
		}
	}
	
	//PutMapping
	public Mesa alterMesa(Long id, Mesa novaMesa)
	{
		Mesa mesa0 = repository.findById(id)
				.orElseThrow(() -> 
				new MesaException("Mesa com id '" + id + "' nao encontrado."));
				
		try {
	        Class<?> mesaClass = Mesa.class;
	        Field[] fields = mesaClass.getDeclaredFields();

	        for (Field field : fields) {
	        	if (!field.equals("idMesa"))
	        	{	        		
	        		field.setAccessible(true);
	        		Object value = field.get(novaMesa);
	        		if (value != null) {
	        			field.set(mesa0, value);
	        		}
	        	}
	        }

	        repository.saveAndFlush(mesa0);
	        return novaMesa;
	        
	    } catch (IllegalAccessException e) {
	        throw new MesaException("Erro ao atualizar mesa." + e);
	    }
	}
	
	//Delete mapping
	public Mesa deleteMesa(Long id)
	{
		Mesa mesaDelete = repository.findById(id).
				orElseThrow(() -> new MesaException("Mesa com id '" + id + "' nao encontrado"));
		
		if (mesaDelete != null)
		{
			Mesa mesaSave = mesaDelete;
			repository.delete(mesaDelete);
			return mesaSave;
		}
		else
		{
			throw new MesaException("Impossivel deletar um cadastro nulo.");
		}
	}
}

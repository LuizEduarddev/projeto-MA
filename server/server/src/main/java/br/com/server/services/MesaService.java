package br.com.server.services;

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
				MesaException("Any records founded in 'getProdutoById' with id: " + id));
	}
	
	//PostMapping
	public Mesa addMesa(Mesa mesa)
	{
		if (mesa == null)
		{
			throw new ProdutoException("product must not be null to post.");
		}
		else
		{
			repository.saveAndFlush(mesa);
			return mesa;
		}
	}
}

package br.com.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.server.entities.Pedido;
import br.com.server.exceptions.PedidoException;
import br.com.server.repositorys.PedidoRepository;

@Service
public class PedidoService 
{
	@Autowired
	private PedidoRepository repository;
	
	//Get mapping
	public List<Pedido> getAllPedidos()
	{
		return repository.findAll();
	}
	
	//Post Mapping
	public Pedido getPedidoById(Long id)
	{
		Pedido pedido1 = repository.findById(id)
				.orElseThrow(() -> new PedidoException("No records found for pedidos with id: " + id));
		return pedido1;
	}
	
	//Post Mapping
	public Pedido postPedido(Pedido pedido)
	{
		if (pedido == null)
		{
			throw new PedidoException("pedido must be not null to post.");
		}
		else
		{
			repository.saveAndFlush(pedido);
			return pedido;
		}
	}
	
	
}

package br.com.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.server.entities.Pedido;
import br.com.server.services.PedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController 
{
	@Autowired
	private PedidoService service;
	
	@GetMapping("/get-all")
	public List<Pedido> getAll()
	{
		return service.getAllPedidos();
	}
	
	@PostMapping("/get-by-id")
	public Pedido getById(@RequestBody Long id)
	{
		return service.getPedidoById(id);
	}
	
	@PostMapping("/post")
	public Pedido postPedido(@RequestBody Pedido pedido)
	{
		return service.postPedido(pedido);
	}
}

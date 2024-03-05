package br.com.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.server.entities.Cliente;
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
	
	@PostMapping("/get-by-id/{id}")
	public Pedido getById(@PathVariable Long id)
	{
		return service.getPedidoById(id);
	}
	
	@PostMapping("/get-by-mesa-id/{id}")
	public Pedido getByMesaId(@PathVariable Long id)
	{
		return service.getPedidoByMesaId(id);
	}
	
	@PostMapping("/get-by-id-cliente/{id}")
	public List<Pedido> getByClienteId(@PathVariable Long id)
	{
		return service.getPedidoByIdCliente(id);
	}
	
	@PutMapping("/alter/{id}/{operation}")
	public Object alter(@PathVariable("id") Long id,@PathVariable("operation") int operation,@RequestBody Pedido pedido)
	{
		return service.alterPedido(id, pedido, operation);
	}
	
	@PostMapping("/check-out-cliente/{mesaId}/{clienteId}")
	public Object checkOutMesa(@PathVariable Long mesaId, @PathVariable Long clienteId)
	{
		return service.checkOutClienteMesa(mesaId, clienteId);
	}
	
	@PostMapping("/post")
	public Pedido post(@RequestBody Pedido pedido)
	{
		return service.postPedido(pedido);
	}

	@DeleteMapping("/delete/{id}")
	public Pedido delete(@PathVariable Long id)
	{
		return service.deletePedido(id);
	}
}

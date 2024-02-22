package br.com.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.server.entities.ItensPedido;
import br.com.server.services.ItensPedidoService;

@RestController
@RequestMapping("/api/pedido/item-pedido")
public class ItemPedidoController {
	
	@Autowired
	private ItensPedidoService service;

	@GetMapping("/get-all")
	public List<ItensPedido> getAll()
	{
		return service.getAllItensPedidos();
	}
	
	@PostMapping("/get-by-id/{id}")
	public ItensPedido getById(@PathVariable Long id)
	{
		return service.getItensPedidoById(id);
	}

	@PostMapping("/post/{idPedido}/{idProduto}")
	public ItensPedido post(@RequestBody ItensPedido itemPedido, @PathVariable Long idPedido, @PathVariable Long idProduto)
	{
		return service.postItemPedido(itemPedido, idPedido, idProduto);
	}
	
	@DeleteMapping("/delete/{id}")
	public ItensPedido delete(@PathVariable Long id)
	{
		return service.deleteItemPedido(id);
	}
	
}

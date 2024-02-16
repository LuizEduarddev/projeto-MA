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

import br.com.server.entities.Carrinho;
import br.com.server.services.CarrinhoService;

@RestController
@RequestMapping("/api/cliente/carrinho")
public class CarrinhoController {
	
	@Autowired
	private CarrinhoService service;
	
	
	@GetMapping("/get-all")
	public List<Carrinho> getAll()
	{
		return service.getAllCarrinho();
	}
	
	@PostMapping("/get-by-id/{id}")
	public Carrinho getById(@PathVariable Long id)
	{
		return service.getCarrinhoById(id);
	}
	
	@PostMapping("/add")
	public Carrinho add(@RequestBody Carrinho carrinho)
	{
		return service.addCarrinho(carrinho);
	}

	@PutMapping("/alter/{id}")
	public Carrinho alter(@PathVariable("id") Long id, @RequestBody Carrinho carrinho)
	{
		return service.alterCarrinho(id, carrinho);
	}
	
	@DeleteMapping("/delete/{id}")
	public Carrinho delete(@PathVariable Long id)
	{
		return service.deleteCarrinho(id);
	}
}

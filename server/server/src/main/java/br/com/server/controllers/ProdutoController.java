package br.com.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.server.entities.Produto;
import br.com.server.services.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController 
{
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/get-all")
	public List<Produto> getAll()
	{
		return service.getAllProduto();
	}
	
	@PostMapping("/get-by-id")
	public Produto getById(@RequestBody Long id)
	{
		return service.getProdutoById(id);
	}
	
	@PostMapping("/get-by-name")
	public Long ProdutoByName(@RequestBody String nomeProduto)
	{
		return service.getProdutoByName(nomeProduto);
	}
	
	@PostMapping("/add")
	public Produto addProduto(@RequestBody Produto produto)
	{
		return service.addProduto(produto);
	}
}

package br.com.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.server.models.entities.Produtos;
import br.com.server.service.ProdutosService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired ProdutosService service;
	
	@GetMapping("/lista")
	private List<Produtos> getAll(){
		return service.getAllProducts();
	}
	
	@PostMapping("/adicionar")
	private ResponseEntity<String> adicionarProduto(@RequestBody Produtos produto){
		ResponseEntity<String> responseProduto = service.createProduto(produto);
		if (responseProduto.equals(HttpStatus.CREATED)) {
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
}

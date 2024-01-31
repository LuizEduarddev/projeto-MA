package br.com.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.server.models.entities.Produtos;
import br.com.server.repository.ProdutoRepository;

@Service
public class ProdutosService {
	
	@Autowired ProdutoRepository repository;
	
	public ResponseEntity<String> createProduto(Produtos produto){
		try {
			repository.saveAndFlush(produto);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}
		catch(Exception err) {
			String error = err.toString();
			return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
		}
	}
	
	public List<Produtos> getAllProducts(){
		return repository.findAll();
	}
}

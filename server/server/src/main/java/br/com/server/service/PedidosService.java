package br.com.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import br.com.server.models.entities.Pedidos;
import br.com.server.repository.PedidosRepository;

@Repository
public class PedidosService {
	
	@Autowired
	private PedidosRepository repository;
	
	public ResponseEntity<String> order(Pedidos pedidos){
		repository.saveAndFlush(pedidos);
		return	new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	public List<Pedidos> pedidos() {
		return repository.findAll();
	}
}

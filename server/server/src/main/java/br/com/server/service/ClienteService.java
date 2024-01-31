package br.com.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.server.models.entities.Cliente;
import br.com.server.repository.ClienteRepository;

@Service
public class ClienteService {
	 
	@Autowired 
	private ClienteRepository repository;
	
	public List<Cliente> clientesCadastrados (){
		return repository.findAll();
	}
	
	public ResponseEntity<String> cadastrarCliente(Cliente cliente){
		repository.saveAndFlush(cliente);
		
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
}

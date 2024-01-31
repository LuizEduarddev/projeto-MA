package br.com.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.server.models.entities.Mesa;
import br.com.server.repository.MesaRepository;

@Service
public class MesaService {
	
	@Autowired
	private MesaRepository repository;
	
	public ResponseEntity<String> createMesa(Mesa mesa){
		try {
			repository.saveAndFlush(mesa);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}
		catch(Exception e) {
			String error = e.toString();
			return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
		}
	}
	
	public List<Mesa> getAllMesa(){
		return repository.findAll();
	}
}

package br.com.server.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.server.models.entities.ItensPedido;
import br.com.server.repository.ItensPedidoRepository;

@Service
public class ItensPedidoService 
{
	private ItensPedidoRepository repository;
	
	public List<ItensPedido> getAll(){
		return repository.findAll();
	}
	
	public ResponseEntity<String> getOrder(ItensPedido itens){
		try {
			repository.saveAndFlush(itens);
			return new ResponseEntity<String>("Order created sucefull", HttpStatus.OK);
		}
		catch(Exception e) {
			String error = e.toString();
			return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
		}
	}
}

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

import br.com.server.models.entities.Cliente;
import br.com.server.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping("/cadastros")
	private List<Cliente> getAllCliente(){
		return service.clientesCadastrados();
	}
	
	@PostMapping("/cadastrar")
	private ResponseEntity<String> postCliente(@RequestBody Cliente cliente){
		ResponseEntity<String> responsePostCliente = service.cadastrarCliente(cliente);
		if (responsePostCliente.equals(HttpStatus.CREATED)) {
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
}

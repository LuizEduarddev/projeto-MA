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

import br.com.server.models.entities.Mesa;
import br.com.server.service.MesaService;

@RestController
@RequestMapping("/api/mesa")
public class MesaController {

	@Autowired
	private MesaService service;
	
	@GetMapping("/ver-mesa")
	public List<Mesa> getAll(){
		return service.getAllMesa();
	}
	
	@PostMapping("/criar")
	public ResponseEntity<String> create(@RequestBody Mesa mesa){
		ResponseEntity<String> responseCreate = service.createMesa(mesa);
		if (responseCreate.equals(HttpStatus.CREATED)) {
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
}

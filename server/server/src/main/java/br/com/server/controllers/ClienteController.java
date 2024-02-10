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

import br.com.server.entities.Cliente;
import br.com.server.entities.dto.clientedto.ClienteLoginDTO;
import br.com.server.services.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping("/get-all")
	public List<Cliente> getAll()
	{
		return service.getAllCliente();
	}
	
	@PostMapping("/check-in")
	public ClienteLoginDTO check(@RequestBody ClienteLoginDTO cliente)
	{
		return service.checkCliente(cliente);
	}
	
	@PostMapping("/get-by-id")
	public Cliente getById(@RequestBody Long id)
	{
		return service.getClienteById(id);
	}
	
	@PostMapping("/add")
	public Cliente add(@RequestBody Cliente cliente)
	{
		return service.addCliente(cliente);
	}
	
	@PutMapping("/alter/{id}")
	public Cliente alter(@PathVariable("id") Long id,@RequestBody Cliente cliente)
	{
		return service.alterCliente(id, cliente);
	}
	
	@DeleteMapping("/delete/{id}")
	public Cliente delete(@PathVariable Long id)
	{
		return service.deleteCliente(id);
	}
}

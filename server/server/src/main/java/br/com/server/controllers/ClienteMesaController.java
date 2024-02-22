package br.com.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.server.entities.Cliente;
import br.com.server.entities.ClienteMesa;
import br.com.server.services.ClienteMesaService;

@RestController
@RequestMapping("/api/mesa/cliente")
public class ClienteMesaController {

	@Autowired
	private ClienteMesaService service;
	
	@GetMapping("/get-all")
	public List<ClienteMesa> getAll()
	{
		return service.getAllMesa();
	}
	
	@PostMapping("/get-by-cliente-id/{id}")
	public ClienteMesa getClienteMesaByCliente(@PathVariable Long id)
	{
		return service.getClienteMesaByIdCliente(id);
	}
	
	@PostMapping("/get-clientes-by-mesa-id/{id}")
	public List<Cliente> getClienteMesaByMesa(@PathVariable Long id)
	{
		return service.getClienteMesaByIdMesa(id);
	}
	
	@PostMapping("/add")
	public ClienteMesa add(@RequestBody ClienteMesa clienteMesa)
	{
		return service.addClienteMesa(clienteMesa);
	}
	
	@DeleteMapping("/delete/{id}")
	public ClienteMesa delete(@RequestBody ClienteMesa clienteMesa)
	{
		return service.deleteClienteMesa(clienteMesa);
	}
}

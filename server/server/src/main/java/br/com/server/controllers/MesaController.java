package br.com.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.server.entities.Mesa;
import br.com.server.services.MesaService;

@RestController
@RequestMapping("/api/mesa")
public class MesaController {
	
	@Autowired
	private MesaService service;
	
	@GetMapping("/get-all")
	public List<Mesa> getAll()
	{
		return service.getAllMesa();
	}
	
	@PostMapping("/get-by-id")
	public Mesa getById(@RequestBody Long id)
	{
		return service.getMesaById(id);
	}
	
	@PostMapping("/add")
	public Mesa add(@RequestBody Mesa mesa)
	{
		return service.addMesa(mesa);
	}
	

}

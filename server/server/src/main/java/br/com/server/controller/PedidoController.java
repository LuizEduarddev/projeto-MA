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

import br.com.server.models.entities.Pedidos;
import br.com.server.service.PedidosService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
	@Autowired
	private PedidosService service;

	@PostMapping("/order")
	private ResponseEntity<String> postarPedido(@RequestBody Pedidos pedido) {

		ResponseEntity<String> responseOrder = service.order(pedido);

		if (responseOrder.equals(HttpStatus.CREATED)) {
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping("/receber")
	private List<Pedidos> verPedidos() {

		return service.pedidos();
	}
}

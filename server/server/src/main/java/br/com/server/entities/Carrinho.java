package br.com.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "carrinho")
@Table(name = "carrinho")
public class Carrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCarrinho;

	@ManyToOne
	@JoinColumn(name = "carrinho_cliente", nullable = false)
	private Cliente carrinhoCliente;

	public Carrinho(Cliente carrinhoCliente) {
		this.carrinhoCliente = carrinhoCliente;
	}

	public Long getIdCarrinho() {
		return idCarrinho;
	}

	public void setIdCarrinho(Long idCarrinho) {
		this.idCarrinho = idCarrinho;
	}

	public Cliente getCarrinhoCliente() {
		return carrinhoCliente;
	}

	public void setCarrinhoCliente(Cliente carrinhoCliente) {
		this.carrinhoCliente = carrinhoCliente;
	}

}

package br.com.server.entities;

import jakarta.persistence.Column;
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

	@Column(name = "id_cliente_carrinho", unique = false, nullable = false)
	private Long idClienteCarrinho;

	public Carrinho() {}
	
	public Carrinho(Long idClienteCarrinho) {
		this.idClienteCarrinho = idClienteCarrinho;
	}

	public Long getIdClienteCarrinho() {
		return idClienteCarrinho;
	}

	public void setIdClienteCarrinho(Long idClienteCarrinho) {
		this.idClienteCarrinho = idClienteCarrinho;
	}

	public Long getIdCarrinho() {
		return idCarrinho;
	}

	public void setIdCarrinho(Long idCarrinho) {
		this.idCarrinho = idCarrinho;
	}
	
}

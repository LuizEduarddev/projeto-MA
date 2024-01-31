package br.com.server.models.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ItensPedidoPk implements Serializable
{
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produtos produto;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedidos pedidos;

	public ItensPedidoPk() {}
	
	public ItensPedidoPk(Produtos produto, Pedidos pedidos) {
		this.produto = produto;
		this.pedidos = pedidos;
	}

	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
	}

	public Pedidos getPedidos() {
		return pedidos;
	}

	public void setPedidos(Pedidos pedidos) {
		this.pedidos = pedidos;
	}
	
	
}
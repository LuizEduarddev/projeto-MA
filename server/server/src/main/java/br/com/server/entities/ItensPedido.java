package br.com.server.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "itens_pedidos")
@Table(name = "itens_pedidos")
public class ItensPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idItensPedido;

	@Column(name = "id_pedido", nullable = false, unique = false)
	private Long idPedido;

	@Column(name = "id_produto", nullable = false, unique = false)
	private Long idProduto;

	@Column(name = "quantidade_produto", nullable = false, unique = false)
	private int quantidadeProduto;

	@Column(name = "total_item_pedido", nullable = false, unique = false)
	private double totalItemPedido;

	public ItensPedido() {
	}

	public ItensPedido(Long idPedido, Long idProduto, int quantidadeProduto, float totalItemPedido) {
		this.idPedido = idPedido;
		this.idProduto = idProduto;
		this.quantidadeProduto = quantidadeProduto;
		this.totalItemPedido = totalItemPedido;
	}

	public double getTotalItemPedido() {
		return totalItemPedido;
	}

	public void setTotalItemPedido(double totalItemPedido) {
		this.totalItemPedido = totalItemPedido;
	}

	public Long getIdItensPedido() {
		return idItensPedido;
	}

	public void setIdItensPedido(Long idItensPedido) {
		this.idItensPedido = idItensPedido;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public int getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(int quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

}

package br.com.server.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Produtos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produtos")
	private Long id;

	@Column(name = "nome_produto", nullable = false)
	private String nome;

	@Column(name = "preco_produto", nullable = false)
	private double preco;

	public Produtos(String nomeProduto, double precoProduto) {
		this.nome = nomeProduto;
		this.preco = precoProduto;
	}

	public Long getIdProdutos() {
		return id;
	}

	public void setIdProdutos(Long idProdutos) {
		this.id = idProdutos;
	}

	public String getNomeProduto() {
		return nome;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nome = nomeProduto;
	}

	public double getPrecoProduto() {
		return preco;
	}

	public void setPrecoProduto(double precoProduto) {
		this.preco = precoProduto;
	}

}

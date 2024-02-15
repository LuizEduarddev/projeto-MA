package br.com.server.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "produtos")
@Table(name = "produtos")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idProduto;

	@Column(name = "nome_produto", nullable = false, unique = true)
	private String nomeProduto;

	@Column(name = "preco_produto", nullable = false)
	private Double precoProduto;

	@Column(name = "descricao_produto", nullable = false, unique = false)
	private String descricaoProduto;

	@Column(name = "promo_produto", nullable = false, unique = false)
	private int promoProduto;

	public Produto() {
	}

	public Produto(String nomeProduto, Double precoProduto, String descricaoProduto, int promoProduto) {
		this.nomeProduto = nomeProduto;
		this.precoProduto = precoProduto;
		this.descricaoProduto = descricaoProduto;
		this.promoProduto = promoProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public int getPromoProduto() {
		return promoProduto;
	}

	public void setPromoProduto(int promoProduto) {
		this.promoProduto = promoProduto;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(Double precoProduto) {
		this.precoProduto = precoProduto;
	}

}

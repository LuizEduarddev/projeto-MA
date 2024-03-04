package br.com.server.entities.itensPedidos.dto;

public class ItensPedidosDTO {

	private int quantidadeProduto;
	private double precoProduto;
	private String nomeProduto;
	

	public ItensPedidosDTO() {
	}

	public ItensPedidosDTO(int quantidadeProduto, double precoProduto, String nomeProduto) {
		this.quantidadeProduto = quantidadeProduto;
		this.precoProduto = precoProduto;
		this.nomeProduto = nomeProduto;
	}

	public double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
	}

	public int getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(int quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

}

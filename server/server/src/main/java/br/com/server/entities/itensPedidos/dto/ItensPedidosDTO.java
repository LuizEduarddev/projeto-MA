package br.com.server.entities.itensPedidos.dto;

public class ItensPedidosDTO {
	
	private int quantidadeProduto;

	private String nomeProduto;
	
	public ItensPedidosDTO() {}

	public ItensPedidosDTO(int quantidadeProduto, String nomeProduto) {
		this.quantidadeProduto = quantidadeProduto;
		this.nomeProduto = nomeProduto;
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

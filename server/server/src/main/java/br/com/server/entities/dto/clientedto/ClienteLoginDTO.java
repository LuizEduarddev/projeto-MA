package br.com.server.entities.dto.clientedto;

import br.com.server.entities.Cliente;

public class ClienteLoginDTO {

	private String cpfDTO;
	private String senhaDTO;
	private String nomeClienteDTO;
	private Long idClienteDTO;

	public ClienteLoginDTO(String cpfDTO, String senhaDTO, String nomeClienteDTO, Long idClienteDTO) {
		this.cpfDTO = cpfDTO;
		this.senhaDTO = senhaDTO;
		this.nomeClienteDTO = nomeClienteDTO;
		this.idClienteDTO = idClienteDTO;
	}

	public ClienteLoginDTO(Cliente cliente) {
		this.cpfDTO = cliente.getCpfCliente();
		this.senhaDTO = cliente.getSenhaCliente();
		this.nomeClienteDTO = cliente.getNomeCliente();
		this.idClienteDTO = cliente.getIdCliente();
	}

	public Long getIdClienteDTO() {
		return idClienteDTO;
	}

	public void setIdClienteDTO(Long idClienteDTO) {
		this.idClienteDTO = idClienteDTO;
	}

	public String getNomeClienteDTO() {
		return nomeClienteDTO;
	}

	public void setNomeClienteDTO(String nomeClienteDTO) {
		this.nomeClienteDTO = nomeClienteDTO;
	}

	public ClienteLoginDTO() {
	}

	public String getCpfDTO() {
		return cpfDTO;
	}

	public void setCpfDTO(String cpfDTO) {
		this.cpfDTO = cpfDTO;
	}

	public String getSenhaDTO() {
		return senhaDTO;
	}

	public void setSenhaDTO(String tokenDTO) {
		this.senhaDTO = tokenDTO;
	}
}

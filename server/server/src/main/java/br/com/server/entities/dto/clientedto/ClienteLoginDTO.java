package br.com.server.entities.dto.clientedto;

public class ClienteLoginDTO {

	private String cpfDTO;
	private String senhaDTO;
	private String nomeClienteDTO;

	public ClienteLoginDTO(String cpfDTO, String senhaDTO, String nomeClienteDTO) {
		this.cpfDTO = cpfDTO;
		this.senhaDTO = senhaDTO;
		this.nomeClienteDTO = nomeClienteDTO;
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

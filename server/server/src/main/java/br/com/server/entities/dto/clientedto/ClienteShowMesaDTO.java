package br.com.server.entities.dto.clientedto;

import jakarta.persistence.Embeddable;

@Embeddable
public class ClienteShowMesaDTO {

	private String nomeClienteDTO;
	private Long idClienteDTO;

	public ClienteShowMesaDTO() {
	}

	public ClienteShowMesaDTO(String nomeClienteDTO, Long idClienteDTO) {
		this.nomeClienteDTO = nomeClienteDTO;
		this.idClienteDTO = idClienteDTO;
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

}

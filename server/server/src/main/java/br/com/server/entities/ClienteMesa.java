package br.com.server.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "cliente_mesa")
@Table(name = "cliente_mesa")
public class ClienteMesa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idClienteMesa;

	@Column(name = "id_cliente", unique = true, nullable = false)
	private Long idCliente;

	@Column(name = "id_mesa", unique = false, nullable = false)
	private Long idMesa;

	public ClienteMesa() {}
	
	public ClienteMesa(Long idCliente, Long idMesa) {
		this.idCliente = idCliente;
		this.idMesa = idMesa;
	}

	public Long getIdClienteMesa() {
		return idClienteMesa;
	}

	public void setIdClienteMesa(Long idClienteMesa) {
		this.idClienteMesa = idClienteMesa;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(Long idMesa) {
		this.idMesa = idMesa;
	}

}

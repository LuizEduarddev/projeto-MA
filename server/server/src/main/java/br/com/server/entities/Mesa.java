package br.com.server.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "mesa")
@Table(name = "mesa")
public class Mesa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idMesa;

	@Column(name = "mesa_em_uso", unique = false, nullable = false)
	private boolean mesaEmUso;

	public Mesa() {
	}

	public Mesa(boolean mesaEmUso) {
		super();
		this.mesaEmUso = mesaEmUso;
	}

	public boolean isMesaEmUso() {
		return mesaEmUso;
	}

	public void setMesaEmUso(boolean mesaEmUso) {
		this.mesaEmUso = mesaEmUso;
	}

	public Long getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(Long idMesa) {
		this.idMesa = idMesa;
	}

}

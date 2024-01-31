package br.com.server.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table
public class Cliente {

	@Id
	@Column(name = "id_cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_cliente", nullable = false)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "mesa_id", nullable = false, unique = true)
	private Mesa mesa;

	@NotEmpty
	@Transient
	private Long mesaId;

	@PostLoad
	private void onLoad() {
		mesa = new Mesa();
		mesa.setId(mesaId);
	}

	public Cliente() {
	}

	public Cliente(String nome, Mesa mesa) {
		this.nome = nome;
		this.mesa = mesa;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public Long getMesaId() {
		return mesaId;
	}

	public void setMesaId(Long mesaId) {
		this.mesaId = mesaId;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

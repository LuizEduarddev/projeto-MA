package br.com.server.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "cliente")
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCliente;

	@Column(name = "cpf", unique = true, nullable = false)
	private String cpfCliente;

	@Column(name = "numero", unique = true, nullable = true)
	private String numeroCliente;

	@Column(name = "endereco_cliente", unique = false, nullable = true)
	private String enderecoCliente;

	@Column(name = "idade_cliente", unique = false, nullable = true)
	private String idadeCliente;

	@Column(name = "nome_cliente", unique = false, nullable = false)
	private String nomeCliente;

	@Column(name = "birth_day", unique = false, nullable = true)
	private Date aniversarioCliente;

	@Column(name = "senha_cliente", unique = false, nullable = false)
	private String senhaCliente;

	public Cliente() {
	}

	public Cliente(String cpfCliente, String numeroCliente, String enderecoCliente, String idadeCliente,
			String nomeCliente, Date aniversarioCliente, String senhaCliente) {
		this.cpfCliente = cpfCliente;
		this.numeroCliente = numeroCliente;
		this.enderecoCliente = enderecoCliente;
		this.idadeCliente = idadeCliente;
		this.nomeCliente = nomeCliente;
		this.aniversarioCliente = aniversarioCliente;
		this.senhaCliente = senhaCliente;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public String getSenhaCliente() {
		return senhaCliente;
	}

	public void setSenhaCliente(String senhaCliente) {
		this.senhaCliente = senhaCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String getIdadeCliente() {
		return idadeCliente;
	}

	public void setIdadeCliente(String idadeCliente) {
		this.idadeCliente = idadeCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Date getAniversarioCliente() {
		return aniversarioCliente;
	}

	public void setAniversarioCliente(Date aniversarioCliente) {
		this.aniversarioCliente = aniversarioCliente;
	}

}

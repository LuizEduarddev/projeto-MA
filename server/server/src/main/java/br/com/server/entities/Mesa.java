package br.com.server.entities;

import java.util.List;

import br.com.server.entities.dto.clientedto.ClienteShowMesaDTO;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "mesa")
@Table(name = "mesa")
public class Mesa {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mesa_seq")
    @SequenceGenerator(name = "mesa_seq", sequenceName = "mesa_sequence", initialValue = 1, allocationSize = 1)
	private Long idMesa;

	@Column(name = "mesa_em_uso", unique = false, nullable = false)
	private boolean mesaEmUso;

	@ElementCollection
    @CollectionTable(name="cliente_show_mesa")
    private List<ClienteShowMesaDTO> clientesMesa;

	@Column(name = "valor_total_mesa", unique = false, nullable = false)
	private float valorTotalMesa;

	public Mesa() {
	}

	public Mesa(boolean mesaEmUso, List<ClienteShowMesaDTO> clientesMesa, float valorTotalMesa) {
		this.mesaEmUso = mesaEmUso;
		this.clientesMesa = clientesMesa;
		this.valorTotalMesa = valorTotalMesa;
	}

	public float getValorTotalMesa() {
		return valorTotalMesa;
	}

	public void setValorTotalMesa(float valorTotalMesa) {
		this.valorTotalMesa = valorTotalMesa;
	}

	public List<ClienteShowMesaDTO> getClientesMesa() {
		return clientesMesa;
	}

	public void setClientesMesa(List<ClienteShowMesaDTO> clientesMesa) {
		this.clientesMesa = clientesMesa;
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

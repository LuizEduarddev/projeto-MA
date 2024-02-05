package br.com.server.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "pedido")
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idPedido;

	@Column(name = "mesa_pedido", nullable = false)
	private Mesa mesaPedido;

	@Column(name = "data_pedido")
	private Date dataPedido;

	public Pedido(Mesa mesaPedido, Date dataPedido) {
		this.mesaPedido = mesaPedido;
		this.dataPedido = dataPedido;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Mesa getMesaPedido() {
		return mesaPedido;
	}

	public void setMesaPedido(Mesa mesaPedido) {
		this.mesaPedido = mesaPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

}

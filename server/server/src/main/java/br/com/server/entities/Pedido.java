package br.com.server.entities;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPedido;

	@Column(name = "id_mesa_pedido", unique = false, nullable = false)
	private Long idMesaPedido;

	@Column(name = "id_cliente_pedido", unique = false, nullable = false)
	private Long idClientePedido;

	@Column(name = "data_pedido", unique = false, nullable = false)
	private String dataPedido;

	@Column(name = "hora_pedido", unique = false, nullable = false)
	private String horaPedido;

	@Column(name = "pedido_finalizado", unique = false, nullable = false)
	private boolean pedidoFinalizado;

	@Column(name = "total_pedido", unique = false, nullable = false)
	private float totalPedido;

	public Pedido() {
	}

	public Pedido(Long idMesaPedido, Long idClientePedido, String dataPedido, String horaPedido,
			boolean pedidoFinalizado, float totalPedido) {
		this.idMesaPedido = idMesaPedido;
		this.idClientePedido = idClientePedido;
		this.dataPedido = dataPedido;
		this.horaPedido = horaPedido;
		this.pedidoFinalizado = pedidoFinalizado;
		this.totalPedido = totalPedido;
	}

	public Long getIdClientePedido() {
		return idClientePedido;
	}

	public void setIdClientePedido(Long idClientePedido) {
		this.idClientePedido = idClientePedido;
	}

	public float getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(float totalPedido) {
		this.totalPedido = totalPedido;
	}

	public String getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(String horaPedido) {
		this.horaPedido = horaPedido;
	}

	public boolean isPedidoFinalizado() {
		return pedidoFinalizado;
	}

	public void setPedidoFinalizado(boolean pedidoFinalizado) {
		this.pedidoFinalizado = pedidoFinalizado;
	}

	public Long getIdMesaPedido() {
		return idMesaPedido;
	}

	public void setIdMesaPedido(Long idMesaPedido) {
		this.idMesaPedido = idMesaPedido;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

}

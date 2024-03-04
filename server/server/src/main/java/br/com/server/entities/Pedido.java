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

	@Column(name = "pedido_pronto", unique = false, nullable = false)
	private boolean pedidoPronto;

	@Column(name = "total_pedido", unique = false, nullable = false)
	private double totalPedido;

	@Column(name = "hora_pedido_finalizado", unique = false, nullable = false)
	private String horaPedidoFinalizado;

	@Column(name = "pedido_pago", unique = false, nullable = false)
	private boolean pedidoPago;

	public Pedido() {
	}

	public Pedido(Long idMesaPedido, Long idClientePedido, String dataPedido, String horaPedido, boolean pedidoPronto,
			double totalPedido, String horaPedidoFinalizado, boolean pedidoPago) {
		this.idMesaPedido = idMesaPedido;
		this.idClientePedido = idClientePedido;
		this.dataPedido = dataPedido;
		this.horaPedido = horaPedido;
		this.pedidoPronto = pedidoPronto;
		this.totalPedido = totalPedido;
		this.horaPedidoFinalizado = horaPedidoFinalizado;
		this.pedidoPago = pedidoPago;
	}

	public Long getIdClientePedido() {
		return idClientePedido;
	}

	public void setIdClientePedido(Long idClientePedido) {
		this.idClientePedido = idClientePedido;
	}

	public double getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(double totalPedido) {
		this.totalPedido = totalPedido;
	}

	public String getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(String horaPedido) {
		this.horaPedido = horaPedido;
	}

	public boolean ispedidoPronto() {
		return pedidoPronto;
	}

	public void setpedidoPronto(boolean pedidoPronto) {
		this.pedidoPronto = pedidoPronto;
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

	public boolean isPedidoPago() {
		return pedidoPago;
	}

	public void setPedidoPago(boolean pedidoPago) {
		this.pedidoPago = pedidoPago;
	}

	public String getHoraPedidoFinalizado() {
		return horaPedidoFinalizado;
	}

	public void setHoraPedidoFinalizado(String horaPedidoFinalizado) {
		this.horaPedidoFinalizado = horaPedidoFinalizado;
	}

}

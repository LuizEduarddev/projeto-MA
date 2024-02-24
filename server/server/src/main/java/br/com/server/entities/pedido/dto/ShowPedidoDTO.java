package br.com.server.entities.pedido.dto;

public class ShowPedidoDTO {

	private Long idMesaPedidoDTO;

	private Long idClientePedidoDTO;

	private String dataPedidoDTO;

	private String horaPedidoDTO;

	private boolean pedidoProntoDTO;

	private double totalPedidoDTO;

	private boolean pedidoFinalizadoDTO;

	private String horaPedidoFinalizadoDTO;

	private boolean pedidoPagoDTO;

	private int quantidadeProdutoDTO;

	private double totalItemPedidoDTO;

	private double nomeProduto;

	ShowPedidoDTO(){}
	
	public ShowPedidoDTO(Long idMesaPedidoDTO, Long idClientePedidoDTO, String dataPedidoDTO, String horaPedidoDTO,
			boolean pedidoProntoDTO, double totalPedidoDTO, boolean pedidoFinalizadoDTO, String horaPedidoFinalizadoDTO,
			boolean pedidoPagoDTO, int quantidadeProdutoDTO, double totalItemPedidoDTO, double nomeProduto) {
		this.idMesaPedidoDTO = idMesaPedidoDTO;
		this.idClientePedidoDTO = idClientePedidoDTO;
		this.dataPedidoDTO = dataPedidoDTO;
		this.horaPedidoDTO = horaPedidoDTO;
		this.pedidoProntoDTO = pedidoProntoDTO;
		this.totalPedidoDTO = totalPedidoDTO;
		this.pedidoFinalizadoDTO = pedidoFinalizadoDTO;
		this.horaPedidoFinalizadoDTO = horaPedidoFinalizadoDTO;
		this.pedidoPagoDTO = pedidoPagoDTO;
		this.quantidadeProdutoDTO = quantidadeProdutoDTO;
		this.totalItemPedidoDTO = totalItemPedidoDTO;
		this.nomeProduto = nomeProduto;
	}

	public Long getIdMesaPedidoDTO() {
		return idMesaPedidoDTO;
	}

	public void setIdMesaPedidoDTO(Long idMesaPedidoDTO) {
		this.idMesaPedidoDTO = idMesaPedidoDTO;
	}

	public Long getIdClientePedidoDTO() {
		return idClientePedidoDTO;
	}

	public void setIdClientePedidoDTO(Long idClientePedidoDTO) {
		this.idClientePedidoDTO = idClientePedidoDTO;
	}

	public String getDataPedidoDTO() {
		return dataPedidoDTO;
	}

	public void setDataPedidoDTO(String dataPedidoDTO) {
		this.dataPedidoDTO = dataPedidoDTO;
	}

	public String getHoraPedidoDTO() {
		return horaPedidoDTO;
	}

	public void setHoraPedidoDTO(String horaPedidoDTO) {
		this.horaPedidoDTO = horaPedidoDTO;
	}

	public boolean isPedidoProntoDTO() {
		return pedidoProntoDTO;
	}

	public void setPedidoProntoDTO(boolean pedidoProntoDTO) {
		this.pedidoProntoDTO = pedidoProntoDTO;
	}

	public double getTotalPedidoDTO() {
		return totalPedidoDTO;
	}

	public void setTotalPedidoDTO(double totalPedidoDTO) {
		this.totalPedidoDTO = totalPedidoDTO;
	}

	public boolean isPedidoFinalizadoDTO() {
		return pedidoFinalizadoDTO;
	}

	public void setPedidoFinalizadoDTO(boolean pedidoFinalizadoDTO) {
		this.pedidoFinalizadoDTO = pedidoFinalizadoDTO;
	}

	public String getHoraPedidoFinalizadoDTO() {
		return horaPedidoFinalizadoDTO;
	}

	public void setHoraPedidoFinalizadoDTO(String horaPedidoFinalizadoDTO) {
		this.horaPedidoFinalizadoDTO = horaPedidoFinalizadoDTO;
	}

	public boolean isPedidoPagoDTO() {
		return pedidoPagoDTO;
	}

	public void setPedidoPagoDTO(boolean pedidoPagoDTO) {
		this.pedidoPagoDTO = pedidoPagoDTO;
	}

	public int getQuantidadeProdutoDTO() {
		return quantidadeProdutoDTO;
	}

	public void setQuantidadeProdutoDTO(int quantidadeProdutoDTO) {
		this.quantidadeProdutoDTO = quantidadeProdutoDTO;
	}

	public double getTotalItemPedidoDTO() {
		return totalItemPedidoDTO;
	}

	public void setTotalItemPedidoDTO(double totalItemPedidoDTO) {
		this.totalItemPedidoDTO = totalItemPedidoDTO;
	}

	public double getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(double nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

}

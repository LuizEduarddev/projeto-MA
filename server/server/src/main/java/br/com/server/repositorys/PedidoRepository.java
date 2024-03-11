package br.com.server.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.server.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	List<Pedido> findByIdMesaPedido(Long id);
	List<Pedido> findByIdClientePedido(Long id);
}

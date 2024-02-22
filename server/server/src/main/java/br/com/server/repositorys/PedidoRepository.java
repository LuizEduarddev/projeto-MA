package br.com.server.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.server.entities.Pedido;
import br.com.server.entities.Produto;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	Pedido findByIdMesaPedido(Long id);
}

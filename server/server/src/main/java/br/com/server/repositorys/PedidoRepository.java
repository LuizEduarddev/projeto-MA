package br.com.server.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.server.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}

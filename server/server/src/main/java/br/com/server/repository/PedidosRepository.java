package br.com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.server.models.entities.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long>{

}

package br.com.server.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.server.entities.ItensPedido;

public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Long>{

}

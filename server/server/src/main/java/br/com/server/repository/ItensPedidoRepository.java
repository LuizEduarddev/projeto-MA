package br.com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.server.models.entities.ItensPedido;
import br.com.server.models.entities.ItensPedidoPk;

public interface ItensPedidoRepository extends JpaRepository<ItensPedido, ItensPedidoPk>{

}

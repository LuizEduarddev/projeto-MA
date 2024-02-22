package br.com.server.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.server.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Cliente findByCpfCliente(String cpf);
	List<Cliente> findByIdClienteIn(List<Long> ids);
}

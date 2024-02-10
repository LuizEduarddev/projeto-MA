package br.com.server.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.server.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Cliente findByCpfCliente(String cpf);
}

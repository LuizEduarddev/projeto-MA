package br.com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.server.models.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}

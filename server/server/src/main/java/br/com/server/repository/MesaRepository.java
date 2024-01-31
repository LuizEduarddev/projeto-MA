package br.com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.server.models.entities.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Long>{

}

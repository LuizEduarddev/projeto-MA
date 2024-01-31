package br.com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.server.models.entities.Produtos;

public interface ProdutoRepository extends JpaRepository<Produtos, Long>{

}

package br.com.server.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.server.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	Produto findByNomeProduto(String nomeProduto);
}

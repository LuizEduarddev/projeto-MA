package br.com.server.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.server.entities.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{

}

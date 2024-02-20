package br.com.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.server.entities.Produto;
import br.com.server.exceptions.ProdutoException;
import br.com.server.repositorys.ProdutoRepository;

@Service
public class ProdutoService 
{
	@Autowired
	private ProdutoRepository repository;
	
	//GetMapping
	public List<Produto> getAllProduto()
	{
		return repository.findAll();
	}
	
	//PostMapping
	public Long getProdutoByName(String nome)
	{
		Produto optionalProduto = repository.findByNomeProduto(nome);
		if (optionalProduto == null)
		{
			throw new ProdutoException("Any products founded with name '" + nome + "'");
		}
		else
		{
			return optionalProduto.getIdProduto();		
		}
	}
	
	//PostMapping
	public Produto getProdutoById(Long id)
	{
		return repository.findById(id)
			.orElseThrow(() -> new 
			ProdutoException("Any records founded in 'getProdutoById' with id: " + id));
	}
	
	//PostMapping
	public Produto addProduto(Produto produto)
	{
		if (produto == null)
		{
			throw new ProdutoException("product must not be null to post.");
		}
		else if (produto.getPromoProduto() != 1 && produto.getPromoProduto() != 0)
		{
			throw new ProdutoException("a promocao do produto nao pode ser diferente de 0 ou 1.");
		}
		else
		{
			repository.saveAndFlush(produto);
			return produto;
		}
	}
	
	//Delete
	public Produto deleteProduto(Long id)
	{
		Produto produto = repository.findById(id)
				.orElseThrow(() -> new ProdutoException("Produto com id '" + "' nao existe"));
		Produto produtoSave = produto;
		
		repository.deleteById(id);
		return produtoSave;
	}
}

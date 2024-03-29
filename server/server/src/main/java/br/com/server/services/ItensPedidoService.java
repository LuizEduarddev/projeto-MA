package br.com.server.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.server.entities.ItensPedido;
import br.com.server.entities.Pedido;
import br.com.server.entities.Produto;
import br.com.server.entities.itensPedidos.dto.ItensPedidosDTO;
import br.com.server.exceptions.ItensPedidoException;
import br.com.server.exceptions.ProdutoException;
import br.com.server.repositorys.ItensPedidoRepository;
import br.com.server.repositorys.PedidoRepository;
import br.com.server.repositorys.ProdutoRepository;

@Service
public class ItensPedidoService {

	@Autowired
	private ItensPedidoRepository repository;
	
	@Autowired 
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	//Get mapping
	public List<ItensPedido> getAllItensPedidos()
	{
		return repository.findAll();
	}

	//Post Mapping
	public ItensPedido getItensPedidoById(Long id)
	{
		ItensPedido itemPedido1 = repository.findById(id)
				.orElseThrow(() -> new ItensPedidoException("Nenhum item de pedido encontrado com o id: " + id));
		
		
		return itemPedido1;
	}
	
	public List<ItensPedidosDTO> getItensPedidoByIdPedido(Long id)
	{
		Pedido pedido0 = pedidoRepository.findById(id)
				.orElseThrow(() -> new ItensPedidoException("Pedido com id '" + "' nao encontrado"));
		List<ItensPedido> listaItensPedido = repository.findByIdPedido(id);
		if (listaItensPedido != null)
		{
			List<ItensPedidosDTO> listaDTO = new ArrayList<ItensPedidosDTO>();
			for(ItensPedido itens: listaItensPedido)
			{
				Produto produto0 = produtoRepository.findById(itens.getIdProduto())
						.orElseThrow(() -> new ProdutoException("Produto com id '" + itens.getIdProduto() + "' nao existe."));
				ItensPedidosDTO itemMap = new ItensPedidosDTO(itens.getQuantidadeProduto(), produto0.getPrecoProduto() ,produto0.getNomeProduto());
				listaDTO.add(itemMap);
			}
			return listaDTO;
		}
		else {
			throw new ItensPedidoException("Cliente nao possui nenhum item de pedido ativo");
		}
	}
	
	//Post Mapping
	public ItensPedido postItemPedido(ItensPedido novoItem, Long idPedido, Long idProduto)
	{
		if (novoItem == null)
		{
			throw new ItensPedidoException("Os itens do pedido nao podem ser nulos.");
		}
		else{
			try {
				Produto produto0 = produtoRepository.findById(idProduto)
						.orElseThrow(() -> new ItensPedidoException("Produto com id '" + idProduto + "' nao encontado"));
						
				Pedido pedido0 = pedidoRepository.findById(idPedido)
						.orElseThrow(() -> new ItensPedidoException("Pedido com id '" + idPedido + "' nao encontado"));
				
				novoItem.setTotalItemPedido(produto0.getPrecoProduto() * novoItem.getQuantidadeProduto());
				novoItem.setIdPedido(pedido0.getIdPedido());
				novoItem.setIdProduto(produto0.getIdProduto());
				repository.saveAndFlush(novoItem);
				return novoItem;
			}
			catch(Exception e)
			{
				throw new ItensPedidoException("Ocorreu um erro ao tentar publicar os itens do pedido.\nERROR: " + e);
			}
		}
	}
	
	//Delete Mapping
	public ItensPedido deleteItemPedido(Long id)
	{
		ItensPedido itemPedidoDelete = repository.findById(id).
				orElseThrow(() -> new ItensPedidoException("Item pedido com id '" + id + "' nao encontrado"));
		
		if (itemPedidoDelete != null)
		{
			ItensPedido ItemPedidoSave = itemPedidoDelete;
			repository.delete(itemPedidoDelete);
			return ItemPedidoSave;
		}
		else
		{
			throw new ItensPedidoException("Impossivel deletar um item de pedido nulo.");
		}
	}
}

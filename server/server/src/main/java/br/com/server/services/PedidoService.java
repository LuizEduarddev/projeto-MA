package br.com.server.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.server.entities.Pedido;
import br.com.server.exceptions.PedidoException;
import br.com.server.repositorys.PedidoRepository;

@Service
public class PedidoService 
{
	@Autowired
	private PedidoRepository repository;
	
	//Get mapping
	public List<Pedido> getAllPedidos()
	{
		return repository.findAll();
	}
	
	//Post Mapping
	public Pedido getPedidoById(Long id)
	{
		Pedido pedido1 = repository.findById(id)
				.orElseThrow(() -> new PedidoException("Nenhum pedido encontrado com o id: " + id));
		return pedido1;
	}
	
	//Post Mapping
	public Pedido postPedido(Pedido novoPedido)
	{
		if (novoPedido == null)
		{
			throw new PedidoException("O pedido nao pode ser nulo.");
		}
		else
		{
			try {				
				Pedido pedido = novoPedido;
				
				Calendar dataAtual = Calendar.getInstance();
				boolean finalizado = false;
				int hora = dataAtual.get(Calendar.HOUR_OF_DAY);
				int minuto = dataAtual.get(Calendar.MINUTE);
				int dia = dataAtual.get(Calendar.DAY_OF_MONTH);
				int mes = dataAtual.get(Calendar.MONTH) + 1; 
				int ano = dataAtual.get(Calendar.YEAR);
				
				String dataPedidoFormatada = String.format("%d/%d/%d %02d:%02d", dia, mes, ano, hora, minuto);
				String horaPedidoFormatada = String.format("%02d:%02d", hora, minuto);
				
				pedido.setDataPedido(dataPedidoFormatada);
				pedido.setHoraPedido(horaPedidoFormatada);
				pedido.setPedidoFinalizado(finalizado);
				
				repository.saveAndFlush(pedido);
				return pedido;
			}
			catch(Exception e)
			{
				throw new PedidoException("Ocorreu um erro ao tentar criar o pedido.\n " + e); 
			}
		}
	}
	
	
}


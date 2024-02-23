package br.com.server.services;

import java.lang.reflect.Field;
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
				boolean pronto = false;
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
				pedido.setpedidoPronto(pronto);
				pedido.setHoraPedidoFinalizado("");
				
				repository.saveAndFlush(pedido);
				return pedido;
			}
			catch(Exception e)
			{
				throw new PedidoException("Ocorreu um erro ao tentar criar o pedido.\n " + e); 
			}
		}
	}
	
	//PostMapping
	public Pedido getPedidoByMesaId(Long id)
	{
		Pedido pedido0 = repository.findByIdMesaPedido(id);
		if (pedido0 == null)
		{
			throw new PedidoException("Nenhum pedido registrado na mesa '" + id + "'");
		}
		else {
			return pedido0;
		}
	}
	
	//PutMapping
	public Pedido alterPedido(Long id, Pedido novoPedido)
	{
		Pedido pedido0 = repository.findById(id)
				.orElseThrow(() -> 
				new PedidoException("Pedido com id '" + id + "' nao encontrado."));
				
		try {
	        Class<?> pedidoClass = Pedido.class;
	        Field[] fields = pedidoClass.getDeclaredFields();

	        for (Field field : fields) {
	            field.setAccessible(true);
	            Object value = field.get(novoPedido);
	            if (value != null) {
	                field.set(pedido0, value);
	            }
	        }

	        repository.saveAndFlush(pedido0);
	        return novoPedido;
	        
	    } catch (IllegalAccessException e) {
	        throw new PedidoException("Erro ao atualizar pedido." + e);
	    }

	}
	
	//Delete Mapping
	public Pedido deletePedido(Long id)
	{
		Pedido pedidoDelete = repository.findById(id).
				orElseThrow(() -> new PedidoException("Pedido com id '" + id + "' nao encontrado"));
		
		if (pedidoDelete != null)
		{
			Pedido pedidoSave = pedidoDelete;
			repository.delete(pedidoDelete);
			return pedidoSave;
		}
		else
		{
			throw new PedidoException("Impossivel deletar um pedido nulo.");
		}
	}
	
	
}


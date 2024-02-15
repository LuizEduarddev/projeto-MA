package br.com.server.services;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.server.entities.Carrinho;
import br.com.server.exceptions.CarrinhoException;
import br.com.server.repositorys.CarrinhoRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository repository;
	
	//Get mapping
	public List<Carrinho> getAllCarrinho()
	{
		return repository.findAll();	
	}
	
	//Post Mapping
	public Carrinho getCarrinhoById(Long id)
	{
		Carrinho carrinho = repository.findById(id)
				.orElseThrow(() -> 
				new CarrinhoException("Carrinho com id '" + id + "' nao encontado"));
	
		return carrinho;
	}
	
	//Post Mapping
	public Carrinho addCarrinho(Carrinho carrinho)
	{
		if (carrinho == null)
		{
			throw new CarrinhoException("O carrinho nao pode estar vazio");
		}
		else
		{
			repository.saveAndFlush(carrinho);
			return carrinho;
		}
	}
	
	//Put Mapping
	public Carrinho alterCarrinho(Long id, Carrinho novoCarrinho)
	{
		Carrinho carrinho0 = repository.findById(id)
				.orElseThrow(() -> 
				new CarrinhoException("Carrinho com id '" + id + "' nao encontrado."));
				
		try {
	        Class<?> carrinhoClass = Carrinho.class;
	        Field[] fields = carrinhoClass.getDeclaredFields();

	        for (Field field : fields) {
	            field.setAccessible(true);
	            Object value = field.get(novoCarrinho);
	            if (value != null) {
	                field.set(carrinho0, value);
	            }
	        }

	        repository.saveAndFlush(carrinho0);
	        return novoCarrinho;
	        
	    } catch (IllegalAccessException e) {
	        throw new CarrinhoException("Erro ao atualizar carrinho." + e);
	    }
	}
	
	//Delete Mapping
		public Carrinho deleteCarrinho(Long id)
		{
			Carrinho carrinhoDelete = repository.findById(id).
					orElseThrow(() -> new CarrinhoException("Carrinho com id '" + id + "' nao encontrado"));
			
			if (carrinhoDelete != null)
			{
				Carrinho carrinhoSave = carrinhoDelete;
				repository.delete(carrinhoDelete);
				return carrinhoSave;
			}
			else
			{
				throw new CarrinhoException("Impossivel deletar um cliente nulo.");
			}
		}
}

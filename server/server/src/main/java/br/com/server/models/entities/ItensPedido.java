package br.com.server.models.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class ItensPedido 
{
	@EmbeddedId 
	private ItensPedidoPk id;

	public ItensPedido() {}
	
	public ItensPedidoPk getId() {
		return id;
	}

	public void setId(ItensPedidoPk id) {
		this.id = id;
	}

	public ItensPedido(ItensPedidoPk id) {
		this.id = id;
	}
	
	
}

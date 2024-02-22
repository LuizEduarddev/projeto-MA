package br.com.server.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.server.entities.Cliente;
import br.com.server.entities.ClienteMesa;

public interface ClienteMesaRepository extends JpaRepository<ClienteMesa, Long>{

	ClienteMesa findByIdCliente(Long id);
	ClienteMesa findByIdMesa(Long id);
	ClienteMesa deleteByIdClienteAndIdMesa(Long idCLiente, Long idMesa);
	ClienteMesa findByIdClienteAndIdMesa(Long idCliente, Long idMesa);
	List<ClienteMesa> findIdClienteByIdMesa(Long id);
}

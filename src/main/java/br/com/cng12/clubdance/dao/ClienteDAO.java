package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cng12.clubdance.entity.ClienteEntity;

@Repository
public interface ClienteDAO extends JpaRepository<ClienteEntity, Long> {

	@Query("select c from ClienteEntity c where c.eventoEntity like concat(?1)")
	List<ClienteEntity> listarClientesEvento(Long idEvento);
}

package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cng12.clubdance.entity.ClienteEntity;

@Repository
public interface ClienteDAO extends JpaRepository<ClienteEntity, Long> {

	@Query("select c from ClienteEntity c where c.eventoEntity like concat(?1)")
	List<ClienteEntity> listarClientesEvento(Long idEvento);

	@Transactional
	@Modifying
	@Query("UPDATE ClienteEntity c SET c.cpf =:cpf, c.nome =:nome, c.tipoIngresso =:tipoIngresso WHERE c.id =:id")
	void editarCliente(@Param("cpf") String cpf, @Param("nome") String nome, @Param("tipoIngresso") String tipoIngresso,
			@Param("id") Long id);

	@Query("select c from ClienteEntity c WHERE c.cpf =:cpf and c.eventoEntity =:idEvento")
	List<ClienteEntity> buscarPorCpf(@Param("cpf") String cpf, @Param("idEvento") Long idEvento);

	@Query("select c from ClienteEntity c WHERE c.eventoEntity =:idEvento and c.tipoIngresso =:tipoIngresso")
	List<ClienteEntity> buscarClientesEvento(@Param("idEvento") Long idEvento,
			@Param("tipoIngresso") String tipoIngresso);
}

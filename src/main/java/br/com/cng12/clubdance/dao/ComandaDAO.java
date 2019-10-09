package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.EventoEntity;

@Repository
public interface ComandaDAO extends JpaRepository<ComandaEntity, Long> {

	@Query("select c from ComandaEntity c WHERE c.clienteEntity =:clienteEntity")
	List<ComandaEntity> buscarComandaComCliente(@Param("clienteEntity") Long clienteEntityId);

	@Transactional
	@Modifying
	@Query("UPDATE ComandaEntity c SET c.precoIngresso =:precoIngresso WHERE c.clienteEntity =:clienteEntity and c.eventoEntity =:eventoEntity")
	void editarValorSeTrocarTipoDeIngresso(@Param("precoIngresso") Double precoIngresso,
			@Param("clienteEntity") ClienteEntity clienteEntity, @Param("eventoEntity") EventoEntity eventoEntity);
}

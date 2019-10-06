package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cng12.clubdance.entity.ComandaEntity;

@Repository
public interface ComandaDAO extends JpaRepository<ComandaEntity, Long>{

	@Query("select c from ComandaEntity c WHERE c.clienteEntity =:clienteEntity")
	List<ComandaEntity> buscarComandaComCliente(@Param("clienteEntity") Long clienteEntityId);
}

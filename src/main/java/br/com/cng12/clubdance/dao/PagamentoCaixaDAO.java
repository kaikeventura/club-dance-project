package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.entity.PagamentoCaixaEntity;

@Repository
public interface PagamentoCaixaDAO extends JpaRepository<PagamentoCaixaEntity, Long> {
	
	@Query("select p from PagamentoCaixaEntity p WHERE p.eventoEntity =:eventoEntity")
	List<PagamentoCaixaEntity> buscarPagamentosQuePossuemEventosVinculados(@Param("eventoEntity") EventoEntity eventoEntity);
	
}

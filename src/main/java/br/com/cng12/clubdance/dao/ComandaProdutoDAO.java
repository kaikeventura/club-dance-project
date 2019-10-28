package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.ComandaProdutoEntity;
import br.com.cng12.clubdance.entity.ProdutoEntity;

@Repository
public interface ComandaProdutoDAO extends JpaRepository<ComandaProdutoEntity, Long> {

	@Query("select c from ComandaProdutoEntity c WHERE c.comandaEntity =:comandaEntity")
	List<ComandaProdutoEntity> buscarLancamentosDaComanda(@Param("comandaEntity") ComandaEntity comandaEntity);

	@Query("select c from ComandaProdutoEntity c WHERE c.produtoEntity =:produtoEntity")
	List<ComandaProdutoEntity> buscarComandasQuePossuemProdutosVinculados(
			@Param("produtoEntity") ProdutoEntity produtoEntity);
}

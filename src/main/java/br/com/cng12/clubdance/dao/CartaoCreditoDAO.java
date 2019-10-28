package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cng12.clubdance.entity.CartaoCreditoEntity;
import br.com.cng12.clubdance.entity.FornecedorEntity;

@Repository
public interface CartaoCreditoDAO extends JpaRepository<CartaoCreditoEntity, Long> {

	@Transactional
	@Modifying
	@Query("UPDATE CartaoCreditoEntity c SET c.limite =:limite WHERE c.id =:id")
	void atualizaLimiteCartao(@Param("limite") Double limite,
			@Param("id") Long id);
	
	@Query("select c from CartaoCreditoEntity c where c.numeroCartao =:numeroCartao")
	List<CartaoCreditoEntity> buscarPorNumeroDoCartao(@Param("numeroCartao") String numeroCartao);
}

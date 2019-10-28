package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cng12.clubdance.entity.CartaoDebitoEntity;

@Repository
public interface CartaoDebitoDAO extends JpaRepository<CartaoDebitoEntity, Long> {

	@Transactional
	@Modifying
	@Query("UPDATE CartaoDebitoEntity c SET c.saldo =:saldo WHERE c.id =:id")
	void atualizarSaldoCartao(@Param("saldo") Double saldo,
			@Param("id") Long id);
	
	@Query("select c from CartaoDebitoEntity c where c.numeroCartao =:numeroCartao")
	List<CartaoDebitoEntity> buscarPorNumeroDoCartao(@Param("numeroCartao") String numeroCartao);
}

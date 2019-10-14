package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cng12.clubdance.entity.NotaFiscalEntity;

@Repository
public interface NotaFiscalDAO extends JpaRepository<NotaFiscalEntity, Long> {

	@Query("select n from NotaFiscalEntity n where n.numero like concat(?1)")
	List<NotaFiscalEntity> buscarPorNumero(Long numero);
}

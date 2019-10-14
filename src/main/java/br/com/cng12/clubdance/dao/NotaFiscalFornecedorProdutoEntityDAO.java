package br.com.cng12.clubdance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cng12.clubdance.entity.NotaFiscalFornecedorProdutoEntity;

@Repository
public interface NotaFiscalFornecedorProdutoEntityDAO extends JpaRepository<NotaFiscalFornecedorProdutoEntity, Long> {

	
}

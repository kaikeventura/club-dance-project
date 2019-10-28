package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cng12.clubdance.entity.NotaFiscalEntity;
import br.com.cng12.clubdance.entity.NotaFiscalFornecedorProdutoEntity;
import br.com.cng12.clubdance.entity.ProdutoEntity;

@Repository
public interface NotaFiscalFornecedorProdutoEntityDAO extends JpaRepository<NotaFiscalFornecedorProdutoEntity, Long> {

	@Query("select nfp from NotaFiscalFornecedorProdutoEntity nfp where nfp.notaFiscalEntity =:notaFiscalEntity")
	NotaFiscalFornecedorProdutoEntity buscarNFPComIdNotaFiscal(
			@Param("notaFiscalEntity") NotaFiscalEntity notaFiscalEntity);

	@Query("select c from NotaFiscalFornecedorProdutoEntity c WHERE c.produtoEntity =:produtoEntity")
	List<NotaFiscalFornecedorProdutoEntity> buscarNotasFiscaisQuePossuemProdutosVinculados(
			@Param("produtoEntity") ProdutoEntity produtoEntity);

}

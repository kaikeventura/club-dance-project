package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.FornecedorEntity;
import br.com.cng12.clubdance.entity.NotaFiscalEntity;
import br.com.cng12.clubdance.entity.NotaFiscalFornecedorProdutoEntity;
import br.com.cng12.clubdance.entity.ProdutoEntity;

public interface NotaFiscalFornecedorProdutoEntityService {

	void salvar(NotaFiscalFornecedorProdutoEntity notaFiscalFornecedorProdutoEntity);

	NotaFiscalFornecedorProdutoEntity buscarNFPComIdNotaFiscal(NotaFiscalEntity notaFiscalEntity);

	NotaFiscalFornecedorProdutoEntity buscarPorId(Long id);

	List<NotaFiscalFornecedorProdutoEntity> buscarNotasFiscaisQuePossuemProdutosVinculados(ProdutoEntity produtoEntity);
	
	List<NotaFiscalFornecedorProdutoEntity> buscarNotasFiscaisQuePossuemFornecedoresVinculados(FornecedorEntity fornecedorEntity);
}

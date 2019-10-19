package br.com.cng12.clubdance.service;

import br.com.cng12.clubdance.entity.NotaFiscalEntity;
import br.com.cng12.clubdance.entity.NotaFiscalFornecedorProdutoEntity;

public interface NotaFiscalFornecedorProdutoEntityService {

	void salvar(NotaFiscalFornecedorProdutoEntity notaFiscalFornecedorProdutoEntity);

	NotaFiscalFornecedorProdutoEntity buscarNFPComIdNotaFiscal(NotaFiscalEntity notaFiscalEntity);
	
	NotaFiscalFornecedorProdutoEntity buscarPorId(Long id);
}

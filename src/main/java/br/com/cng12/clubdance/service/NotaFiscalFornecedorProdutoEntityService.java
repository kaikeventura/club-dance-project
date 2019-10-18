package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.NotaFiscalEntity;
import br.com.cng12.clubdance.entity.NotaFiscalFornecedorProdutoEntity;

public interface NotaFiscalFornecedorProdutoEntityService {

	void salvar(NotaFiscalFornecedorProdutoEntity notaFiscalFornecedorProdutoEntity);

	List<NotaFiscalFornecedorProdutoEntity> buscarNFPComIdNotaFiscal(NotaFiscalEntity notaFiscalEntity);
	
	NotaFiscalFornecedorProdutoEntity buscarPorId(Long id);
}

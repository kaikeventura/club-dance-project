package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.NotaFiscalFornecedorProdutoEntityDAO;
import br.com.cng12.clubdance.entity.NotaFiscalEntity;
import br.com.cng12.clubdance.entity.NotaFiscalFornecedorProdutoEntity;
import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.service.NotaFiscalFornecedorProdutoEntityService;

@Service
public class NotaFiscalFornecedorProdutoEntityServiceImpl implements NotaFiscalFornecedorProdutoEntityService {

	@Autowired
	private NotaFiscalFornecedorProdutoEntityDAO dao;

	@Override
	public void salvar(NotaFiscalFornecedorProdutoEntity notaFiscalFornecedorProdutoEntity) {
		
		dao.save(notaFiscalFornecedorProdutoEntity);
	}

	@Override
	public NotaFiscalFornecedorProdutoEntity buscarNFPComIdNotaFiscal(NotaFiscalEntity notaFiscalEntity) {
		
		return dao.buscarNFPComIdNotaFiscal(notaFiscalEntity);
	}

	@Override
	public NotaFiscalFornecedorProdutoEntity buscarPorId(Long id) {

		return dao.getOne(id);
	}

	@Override
	public List<NotaFiscalFornecedorProdutoEntity> buscarNotasFiscaisQuePossuemProdutosVinculados(
			ProdutoEntity produtoEntity) {
		
		return dao.buscarNotasFiscaisQuePossuemProdutosVinculados(produtoEntity);
	}
	
	
}

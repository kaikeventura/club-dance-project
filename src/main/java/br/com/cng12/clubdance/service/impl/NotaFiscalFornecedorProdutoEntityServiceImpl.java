package br.com.cng12.clubdance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.NotaFiscalFornecedorProdutoEntityDAO;
import br.com.cng12.clubdance.entity.NotaFiscalFornecedorProdutoEntity;
import br.com.cng12.clubdance.service.NotaFiscalFornecedorProdutoEntityService;

@Service
public class NotaFiscalFornecedorProdutoEntityServiceImpl implements NotaFiscalFornecedorProdutoEntityService {

	@Autowired
	private NotaFiscalFornecedorProdutoEntityDAO dao;

	@Override
	public void salvar(NotaFiscalFornecedorProdutoEntity notaFiscalFornecedorProdutoEntity) {
		
		dao.save(notaFiscalFornecedorProdutoEntity);
	}
	
	
}

package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.ComandaProdutoDAO;
import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.ComandaProdutoEntity;
import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.service.ComandaProdutoService;

@Service
public class ComandaProdutoServiceImpl implements ComandaProdutoService {

	@Autowired
	private ComandaProdutoDAO dao;

	@Override
	public void salvar(ComandaProdutoEntity comandaProdutoEntity) {

		dao.save(comandaProdutoEntity);
	}

	@Override
	public List<ComandaProdutoEntity> buscarLancamentosDaComanda(ComandaEntity comandaEntity) {

		return dao.buscarLancamentosDaComanda(comandaEntity);
	}

	@Override
	public List<ComandaProdutoEntity> buscarComandasQuePossuemProdutosVinculados(ProdutoEntity produtoEntity) {

		return dao.buscarComandasQuePossuemProdutosVinculados(produtoEntity);
	}

}

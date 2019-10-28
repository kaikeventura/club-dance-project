package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.ComandaProdutoEntity;
import br.com.cng12.clubdance.entity.ProdutoEntity;

public interface ComandaProdutoService {

	void salvar(ComandaProdutoEntity comandaProdutoEntity);

	List<ComandaProdutoEntity> buscarLancamentosDaComanda(ComandaEntity comandaEntity);

	List<ComandaProdutoEntity> buscarComandasQuePossuemProdutosVinculados(ProdutoEntity produtoEntity);
}

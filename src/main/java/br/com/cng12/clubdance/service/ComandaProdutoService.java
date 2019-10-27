package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.ComandaProdutoEntity;

public interface ComandaProdutoService {

	void salvar(ComandaProdutoEntity comandaProdutoEntity);

	List<ComandaProdutoEntity> buscarLancamentosDaComanda(ComandaEntity comandaEntity);

}

package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.ProdutoEntity;

public interface ProdutoService {

	void salvar(ProdutoEntity produtoEntity);

	List<ProdutoEntity> listar();
}

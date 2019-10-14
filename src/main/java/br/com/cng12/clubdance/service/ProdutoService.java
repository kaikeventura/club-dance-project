package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.ProdutoEntity;

public interface ProdutoService {

	void salvar(ProdutoEntity produtoEntity);

	List<ProdutoEntity> listar();
	
	void editar(String nome, String marca, String unidadeMedida, String status, Long id);

	void excluir(Long id);

	ProdutoEntity buscarPorId(Long id);
	
	List<ProdutoEntity> buscarPorNome(String nome);
}

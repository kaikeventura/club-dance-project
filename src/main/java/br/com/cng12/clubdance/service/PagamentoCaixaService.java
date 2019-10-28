package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.PagamentoCaixaEntity;

public interface PagamentoCaixaService {

	void salvar(PagamentoCaixaEntity pagamentoCaixaEntity);

	List<PagamentoCaixaEntity> listar();
	
	PagamentoCaixaEntity buscarPorId(Long id);
}

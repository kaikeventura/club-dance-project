package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.NotaFiscalEntity;

public interface NotaFiscalService {

	void salvar(NotaFiscalEntity notaFiscalEntity);
	
	NotaFiscalEntity buscarPorId(Long id);
	
	List<NotaFiscalEntity> buscarPorNumero(Long numero);
}

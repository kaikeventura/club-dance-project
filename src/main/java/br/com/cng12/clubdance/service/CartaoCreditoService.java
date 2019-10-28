package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.CartaoCreditoEntity;

public interface CartaoCreditoService {

	void salvar(CartaoCreditoEntity cartaoCreditoEntity);

	List<CartaoCreditoEntity> listar();
	
	CartaoCreditoEntity buscarPorId(Long id);
	
	void atualizaLimiteCartao(Double limite, Long id);
	
	List<CartaoCreditoEntity> buscarPorNumeroDoCartao(String numeroCartao);
}

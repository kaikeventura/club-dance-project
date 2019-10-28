package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.CartaoDebitoEntity;

public interface CartaoDebitoService {

	void salvar(CartaoDebitoEntity cartaoDebitoEntity);

	List<CartaoDebitoEntity> listar();
	
	CartaoDebitoEntity buscarPorId(Long id);
	
	void atualizarSaldoCartao(Double saldo, Long id);
	
	List<CartaoDebitoEntity> buscarPorNumeroDoCartao(String numeroCartao);
}

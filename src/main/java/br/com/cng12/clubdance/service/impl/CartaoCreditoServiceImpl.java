package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.CartaoCreditoDAO;
import br.com.cng12.clubdance.entity.CartaoCreditoEntity;
import br.com.cng12.clubdance.service.CartaoCreditoService;

@Service
public class CartaoCreditoServiceImpl implements CartaoCreditoService {

	@Autowired
	private CartaoCreditoDAO dao;
	
	@Override
	public void salvar(CartaoCreditoEntity cartaoCreditoEntity) {

		dao.save(cartaoCreditoEntity);
	}

	@Override
	public List<CartaoCreditoEntity> listar() {

		return dao.findAll();
	}

	@Override
	public CartaoCreditoEntity buscarPorId(Long id) {

		return dao.getOne(id);
	}

	@Override
	public void atualizaLimiteCartao(Double limite, Long id) {
		
		dao.atualizaLimiteCartao(limite, id);	
	}

	@Override
	public List<CartaoCreditoEntity> buscarPorNumeroDoCartao(String numeroCartao) {
		
		return dao.buscarPorNumeroDoCartao(numeroCartao);
	}

}

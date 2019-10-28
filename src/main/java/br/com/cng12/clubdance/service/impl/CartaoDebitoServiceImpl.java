package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.CartaoDebitoDAO;
import br.com.cng12.clubdance.entity.CartaoDebitoEntity;
import br.com.cng12.clubdance.service.CartaoDebitoService;

@Service
public class CartaoDebitoServiceImpl implements CartaoDebitoService {

	@Autowired
	private CartaoDebitoDAO dao;
	
	@Override
	public void salvar(CartaoDebitoEntity cartaoDebitoEntity) {

		dao.save(cartaoDebitoEntity);
	}

	@Override
	public List<CartaoDebitoEntity> listar() {

		return dao.findAll();
	}

	@Override
	public CartaoDebitoEntity buscarPorId(Long id) {

		return dao.getOne(id);
	}

	@Override
	public void atualizarSaldoCartao(Double saldo, Long id) {

		dao.atualizarSaldoCartao(saldo, id);		
	}

	@Override
	public List<CartaoDebitoEntity> buscarPorNumeroDoCartao(String numeroCartao) {
		
		return dao.buscarPorNumeroDoCartao(numeroCartao);
	}

}

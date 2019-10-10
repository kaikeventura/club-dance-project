package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.ProdutoDAO;
import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoDAO dao;
	
	@Override
	public void salvar(ProdutoEntity produtoEntity) {
		
		dao.save(produtoEntity);
	}

	@Override
	public List<ProdutoEntity> listar() {
		
		return dao.findAll();
	}

}

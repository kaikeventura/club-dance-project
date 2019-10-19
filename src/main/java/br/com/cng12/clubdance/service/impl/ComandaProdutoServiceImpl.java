package br.com.cng12.clubdance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.ComandaProdutoDAO;
import br.com.cng12.clubdance.entity.ComandaProdutoEntity;
import br.com.cng12.clubdance.service.ComandaProdutoService;

@Service
public class ComandaProdutoServiceImpl implements ComandaProdutoService {

	@Autowired
	private ComandaProdutoDAO dao;
	
	@Override
	public void salvar(ComandaProdutoEntity comandaProdutoEntity) {

		dao.save(comandaProdutoEntity);
	}

}

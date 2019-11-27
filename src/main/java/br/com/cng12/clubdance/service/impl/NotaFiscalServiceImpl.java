package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.NotaFiscalDAO;
import br.com.cng12.clubdance.entity.NotaFiscalEntity;
import br.com.cng12.clubdance.service.NotaFiscalService;

@Service
public class NotaFiscalServiceImpl implements NotaFiscalService {

	@Autowired
	private NotaFiscalDAO dao;
	
	@Override
	public void salvar(NotaFiscalEntity notaFiscalEntity) {

		dao.save(notaFiscalEntity);
	}

	@Override
	public NotaFiscalEntity buscarPorId(Long id) {
		
		return dao.getOne(id);
	}

	@Override
	public List<NotaFiscalEntity> buscarPorNumero(Long numero) {

		return dao.buscarPorNumero(numero);
	}

	@Override
	public List<NotaFiscalEntity> listar() {
		
		return dao.findAll();
	}

	@Override
	public NotaFiscalEntity buscarPorNumeroNF(Long numero) {

		return dao.buscarPorNumeroNF(numero);
	}

}

package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.PagamentoCaixaDAO;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.entity.PagamentoCaixaEntity;
import br.com.cng12.clubdance.service.PagamentoCaixaService;

@Service
public class PagamentoCaixaServiceImpl implements PagamentoCaixaService {

	@Autowired
	private PagamentoCaixaDAO dao;
	
	@Override
	public void salvar(PagamentoCaixaEntity pagamentoCaixaEntity) {
		
		dao.save(pagamentoCaixaEntity);
	}

	@Override
	public List<PagamentoCaixaEntity> listar() {

		return dao.findAll();
	}

	@Override
	public PagamentoCaixaEntity buscarPorId(Long id) {

		return dao.getOne(id);
	}

	@Override
	public List<PagamentoCaixaEntity> buscarPagamentosQuePossuemEventosVinculados(EventoEntity eventoEntity) {

		return dao.buscarPagamentosQuePossuemEventosVinculados(eventoEntity);
	}

}

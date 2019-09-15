package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.EventoDAO;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.service.EventoService;

@Service
public class EventoServiceImpl implements EventoService {
	
	@Autowired
	private EventoDAO dao;

	@Override
	public void salvar(EventoEntity eventoEntity) {
		
		dao.save(eventoEntity);
	}

	@Override
	public List<EventoEntity> listar() {
		
		return dao.findAll();
	}

	@Override
	public void editar(EventoEntity eventoEntity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public EventoEntity buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventoEntity> buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

}

package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.EventoEntity;

public interface EventoService {

	void salvar(EventoEntity eventoEntity);

	List<EventoEntity> listar();

	void editar(EventoEntity eventoEntity);

	void excluir(Long id);

	EventoEntity buscarPorId(Long id);

	List<EventoEntity> buscarPorNome(String nome);

}

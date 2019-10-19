package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.ClienteDAO;
import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDAO dao;
	
	@Override
	public void salvar(ClienteEntity clienteEntity) {
		
		dao.save(clienteEntity);		
	}

	@Override
	public void editar(String cpf, String nome, String tipoIngresso, Long id) {

		dao.editarCliente(cpf, nome, tipoIngresso, id);
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClienteEntity buscarPorId(Long id) {
		
		return dao.getOne(id);
	}

	@Override
	public List<ClienteEntity> buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClienteEntity> listaClientesDoEvento(Long idEvento) {

		return dao.listarClientesEvento(idEvento);
	}

	@Override
	public List<ClienteEntity> buscarPorCpf(String cpf, Long idEvento) {
		
		return dao.buscarPorCpf(cpf, idEvento);
	}

	@Override
	public List<ClienteEntity> buscarClientesEvento(Long idEvento, String tipoIngresso) {
		
		return dao.buscarClientesEvento(idEvento, tipoIngresso);
	}

	@Override
	public List<ClienteEntity> buscarClientesDoEvento(EventoEntity eventoEntity) {
		
		return dao.buscarClientesDoEvento(eventoEntity);
	}

}

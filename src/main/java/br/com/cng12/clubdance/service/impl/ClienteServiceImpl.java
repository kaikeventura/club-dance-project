package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.ClienteDAO;
import br.com.cng12.clubdance.entity.ClienteEntity;
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
	public void editar(ClienteEntity clienteEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClienteEntity buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClienteEntity> buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClienteEntity> listaClientesDoEvento(Long idEvento) {
		// TODO Auto-generated method stub
		return dao.listarClientesEvento(idEvento);
	}

}

package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.ClienteEntity;

public interface ClienteService {

	void salvar(ClienteEntity clienteEntity);

	List<ClienteEntity> listar();

	void editar(ClienteEntity clienteEntity);

	void excluir(Long id);

	ClienteEntity buscarPorId(Long id);

	List<ClienteEntity> buscarPorNome(String nome);

}

package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.ClienteEntity;

public interface ClienteService {

	void salvar(ClienteEntity clienteEntity);

	List<ClienteEntity> listaClientesDoEvento(Long idEvento);

	void editar(String cpf, String nome, String tipoIngresso, Long id);

	void excluir(Long id);

	ClienteEntity buscarPorId(Long id);

	List<ClienteEntity> buscarPorNome(String nome);
	
	List<ClienteEntity> buscarPorCpf(String cpf, Long idEvento);

}

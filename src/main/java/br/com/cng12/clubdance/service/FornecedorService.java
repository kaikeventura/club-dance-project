package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.FornecedorEntity;

public interface FornecedorService {

	void salvar(FornecedorEntity fornecedorEntity);

	List<FornecedorEntity> listar();
	
	List<FornecedorEntity> listarFornecedoresAtivos();

	void editar(String nomeFantasia, String razaoSocial, String atividadePrincipal, String cnpj,
			String inscricaoEstadual, String endereco, String bairro, String cidade, String uf, String cep,
			String telefone, String celular, String email, String nomeContato, String status, Long id);

	void excluir(Long id);

	FornecedorEntity buscarPorId(Long id);

	List<FornecedorEntity> buscarPorNome(String nome);
}

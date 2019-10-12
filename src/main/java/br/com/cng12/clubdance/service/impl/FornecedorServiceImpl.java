package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.FornecedorDAO;
import br.com.cng12.clubdance.entity.FornecedorEntity;
import br.com.cng12.clubdance.service.FornecedorService;

@Service
public class FornecedorServiceImpl implements FornecedorService {

	@Autowired
	private FornecedorDAO dao;

	@Override
	public void salvar(FornecedorEntity fornecedorEntity) {

		dao.save(fornecedorEntity);
	}

	@Override
	public List<FornecedorEntity> listar() {

		return dao.findAll();
	}

	@Override
	public void editar(String nomeFantasia, String razaoSocial, String atividadePrincipal, String cnpj,
			String inscricaoEstadual, String endereco, String bairro, String cidade, String uf, String cep,
			String telefone, String celular, String email, String nomeContato, String status, Long id) {

		dao.editar(nomeFantasia, razaoSocial, atividadePrincipal, cnpj, inscricaoEstadual, endereco, bairro, cidade, uf,
				cep, telefone, celular, email, nomeContato, status, id);
	}

	@Override
	public void excluir(Long id) {

		dao.deleteById(id);
	}

	@Override
	public FornecedorEntity buscarPorId(Long id) {

		return dao.getOne(id);
	}

	@Override
	public List<FornecedorEntity> buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

}

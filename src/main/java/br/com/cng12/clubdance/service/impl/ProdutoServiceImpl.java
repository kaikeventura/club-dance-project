package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.ProdutoDAO;
import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoDAO dao;
	
	@Override
	public void salvar(ProdutoEntity produtoEntity) {
		
		dao.save(produtoEntity);
	}

	@Override
	public List<ProdutoEntity> listar() {
		
		return dao.findAll();
	}
	
	@Override
	public List<ProdutoEntity> listarProdutosAtivos() {

		return dao.listarProdutosAtivos();
	}
	
	@Override
	public void editar(String nome, String marca, String unidadeMedida, Double margemLucro, String status, Long id) {
		
		dao.editarProduto(nome, marca, unidadeMedida, margemLucro, status, id);		
	}

	@Override
	public void excluir(Long id) {
		
		dao.deleteById(id);		
	}

	@Override
	public ProdutoEntity buscarPorId(Long id) {

		return dao.getOne(id);
	}

	@Override
	public List<ProdutoEntity> buscarPorNome(String nome) {
		
		return dao.buscarProdutoPorNome(nome);
	}

	@Override
	public void lancarEntradaDeProduto(Double preco, int qtdeEstoque, Long id) {
		
		dao.lancarEntradaDeProduto(preco, qtdeEstoque, id);
	}

	@Override
	public void retirarQtdeEstoque(int qtdeEstoque, Long id) {

		dao.retirarQtdeEstoque(qtdeEstoque, id);
	}

}

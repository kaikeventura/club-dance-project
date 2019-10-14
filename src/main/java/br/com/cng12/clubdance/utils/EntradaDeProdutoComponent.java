package br.com.cng12.clubdance.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.service.ProdutoService;

@Component
public class EntradaDeProdutoComponent {

	@Autowired
	private ProdutoService produtoService;

	public void lancamentoDeEntradaDeProduto(Double valorUnitario, int quantidade, Long idProduto) {

		ProdutoEntity produtoEntity = produtoService.buscarPorId(idProduto);
		int qtdeEstoqueAtual = produtoEntity.getQtdeEstoque();
		Double calculaPrecoProduto = (valorUnitario * produtoEntity.getMargemLucro()) / 100;
		Double precoProduto = valorUnitario + calculaPrecoProduto;

		produtoService.lancarEntradaDeProduto(precoProduto, qtdeEstoqueAtual + quantidade, idProduto);

	}

	public static void main(String[] args) {

		Double valor = 12032.78D;
		Double MLucro = 3.98D;

		Double result = (valor * MLucro) / 100;

		System.out.println(result + valor);
	}

}

package br.com.cng12.clubdance.utils.components;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.service.ProdutoService;

@Component
public class EntradaDeProdutoComponent {

	@Autowired
	private ProdutoService produtoService;

	public void lancamentoDeEntradaDeProduto(BigDecimal valorUnitario, int quantidade, Long idProduto) {

		ProdutoEntity produtoEntity = produtoService.buscarPorId(idProduto);
		int qtdeEstoqueAtual = produtoEntity.getQtdeEstoque();
		BigDecimal margem = BigDecimal.valueOf(produtoEntity.getMargemLucro());
		BigDecimal porcentagem = new BigDecimal(100);
		BigDecimal calculaPrecoProduto = (valorUnitario.multiply(margem)).divide(porcentagem);
		BigDecimal precoProduto = valorUnitario.add(calculaPrecoProduto);

		produtoService.lancarEntradaDeProduto(precoProduto, qtdeEstoqueAtual + quantidade, idProduto);

	}

}

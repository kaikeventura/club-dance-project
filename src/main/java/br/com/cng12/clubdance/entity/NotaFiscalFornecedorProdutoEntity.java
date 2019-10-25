package br.com.cng12.clubdance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "nota_fiscal_fornecedor_produto")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscalFornecedorProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private NotaFiscalEntity notaFiscalEntity;

	@ManyToOne
	private FornecedorEntity fornecedorEntity;

	@ManyToOne
	private ProdutoEntity produtoEntity;

	@NumberFormat(style = Style.CURRENCY, pattern = "###,###,###,##0.00")
	private Double valorUnitario;

	private int qtde;

	public NotaFiscalFornecedorProdutoEntity(NotaFiscalEntity notaFiscalEntity, FornecedorEntity fornecedorEntity,
			ProdutoEntity produtoEntity, Double valorUnitario, int qtde) {
		super();
		this.notaFiscalEntity = notaFiscalEntity;
		this.fornecedorEntity = fornecedorEntity;
		this.produtoEntity = produtoEntity;
		this.valorUnitario = valorUnitario;
		this.qtde = qtde;
	}

}

package br.com.cng12.clubdance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	private Double valorUnitario;
	
	private Double qtde;
	
}

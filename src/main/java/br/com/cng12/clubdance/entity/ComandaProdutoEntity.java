package br.com.cng12.clubdance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "comanda_produto")
@Entity
public class ComandaProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ComandaEntity comandaEntity;
	
	@ManyToOne
	private ProdutoEntity produtoEntity;
	
	@Column(nullable = true)
	private Double valorUnitario;
	
	@Column(nullable = true)
	private int qtde;
	
	@Column(nullable = true)
	private Double valorTotal;
	
}

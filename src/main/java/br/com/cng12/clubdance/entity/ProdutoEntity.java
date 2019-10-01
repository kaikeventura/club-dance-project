package br.com.cng12.clubdance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Table(name = "produto")
@Entity
@AllArgsConstructor
public class ProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true)
	private String nome;
	
	@Column(nullable = true)
	private Double preco;
	
	@Column(nullable = false)
	private Double qtdeEstoque;
	
	@Column(nullable = true)
	private String unidadeMedida;
	
}

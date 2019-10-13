package br.com.cng12.clubdance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "produto")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_produto")
	private Long id;

	@Column(nullable = true, unique = true)
	private String nome;
	
	@Column(nullable = false)
	private String marca;
	
	@Column(nullable = true)
	private Double preco;
	
	@Column(nullable = false)
	private Double qtdeEstoque;
	
	@Column(nullable = true)
	private String unidadeMedida;
	
}

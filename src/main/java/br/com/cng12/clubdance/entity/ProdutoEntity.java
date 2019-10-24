package br.com.cng12.clubdance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

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
	private Long id;

	@Column(nullable = true, unique = true)
	private String nome;
	
	@Column(nullable = false)
	private String marca;
	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,###,###,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(12,2) DEFAULT 0.00")
	private Double preco;
	
	@Column(nullable = false)
	private int qtdeEstoque;
	
	@Column(nullable = true)
	private String unidadeMedida;
	
	@Column(nullable = false)
	private Double margemLucro;
	
	@Column(nullable = true)
	private String status;
	
}

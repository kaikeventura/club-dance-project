package br.com.cng12.clubdance.entity;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "comanda_produto")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ComandaProdutoEntity implements Serializable {

	private static final long serialVersionUID = 7612140320276983428L;

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

package br.com.cng12.clubdance.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "produto")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_nota_fiscal")
	private Long id;
	
	@Column(nullable = true, unique = true)
	private Long numero;
	
	@Column(nullable = true)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataEmissao;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "notafiscal_produto", joinColumns = @JoinColumn(name = "id_nota_fiscal"), inverseJoinColumns = @JoinColumn(name = "id_produto"))
	private Set<ProdutoEntity> produto;
	
}

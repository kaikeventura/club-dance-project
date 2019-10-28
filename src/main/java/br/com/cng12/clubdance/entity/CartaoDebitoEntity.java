package br.com.cng12.clubdance.entity;

import java.io.Serializable;

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
@Table(name = "cartao_debito")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CartaoDebitoEntity implements Serializable {

	private static final long serialVersionUID = 3884262469137121015L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String numeroCartao;
	
	@Column(nullable = false)
	private int senha;

	@Column(nullable = false)
	@NumberFormat(style = Style.CURRENCY, pattern = "###,###,###,##0.00")
	private Double saldo;
	
}

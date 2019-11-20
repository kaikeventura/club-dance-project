package br.com.cng12.clubdance.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "pagamento_caixa")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoCaixaEntity implements Serializable {
	 
	private static final long serialVersionUID = -5606045682395461259L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String formaPagamento;
	
	@Column(nullable = true)
	private String numeroCartao;
	
	@Column(nullable = true)
	private int senha;
	
	@Column(nullable = true)
	@NumberFormat(style = Style.CURRENCY, pattern = "###,###,###,##0.00")
	private Double valor;
	
	@ManyToOne
	private ClienteEntity clienteEntity;
	
	@ManyToOne
	private EventoEntity eventoEntity;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data;
	
}

package br.com.cng12.clubdance.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "nota_fiscal")
@Entity
@NoArgsConstructor
public class NotaFiscalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private Long numero;
	
	@Column(nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataEmissao;
	
	@Column(nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataLancamento;
	
}

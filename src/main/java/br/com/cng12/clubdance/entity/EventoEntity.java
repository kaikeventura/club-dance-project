package br.com.cng12.clubdance.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.Data;

@Data
@Table(name = "evento")
@Entity
public class EventoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Campo obrigatório.")
	@Column(name = "nome_evento", nullable = true)
	private String nome;

	@NotEmpty(message = "Campo obrigatório.")
	@Column(nullable = true)
	private String local;

	@NotNull(message = "Campo obrigatório.")
	@Column(nullable = true)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data;

	@NotEmpty(message = "Campo obrigatório.")
	@Column(nullable = true)
	private String horaInicio;

	@NotEmpty(message = "Campo obrigatório.")
	@Column(nullable = true)
	private String horaTermino;

	@Column(nullable = false)
	private int capacidade;

	@Column(nullable = false)
	private int capacidadeCamarote;

	@NotNull(message = "Campo obrigatório.")
	@NumberFormat(style = Style.CURRENCY, pattern = "###,###,###,##0.00")
	@Column(nullable = false)
	private Double precoIngressoNormal;

	@NotNull(message = "Campo obrigatório.")
	@NumberFormat(style = Style.CURRENCY, pattern = "###,###,###,##0.00")
	@Column(nullable = false)
	private Double precoIngressoVip;

	@NotNull(message = "Campo obrigatório.")
	@NumberFormat(style = Style.CURRENCY, pattern = "###,###,###,##0.00")
	@Column(nullable = false)
	private Double precoIngressoCamarote;

	@Column(nullable = true)
	private String status;

}
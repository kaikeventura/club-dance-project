package br.com.cng12.clubdance.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
@Table(name = "evento")
@Entity
public class EventoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Campo obrigatório.")
	@Column(nullable = true)
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

	@Min(value = 1, message = "Campo obrigatório.")
	@Max(value = 1000, message = "Campo obrigatório.")
	@Column(nullable = false)
	private int capacidade;
	
	@Min(value = 1, message = "Campo obrigatório.")
	@Max(value = 1000, message = "Campo obrigatório.")
	@Column(nullable = false)
	private int capacidadeCamarote;

	@NotNull(message = "Campo obrigatório.")
	@Column(nullable = false)
	private Double precoIngressoNormal;
	
	@NotNull(message = "Campo obrigatório.")
	@Column(nullable = false)
	private Double precoIngressoVip;
	
	@NotNull(message = "Campo obrigatório.")
	@Column(nullable = false)
	private Double precoIngressoCamarote;
	
//	@NotNull
//	@Size(min = 1, max = 200, message = "O campo Status é obrigatório.")
	@Column(nullable = true)
	private String status;
	
}
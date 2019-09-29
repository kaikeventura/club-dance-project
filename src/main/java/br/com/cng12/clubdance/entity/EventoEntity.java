package br.com.cng12.clubdance.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@Column(nullable = true)
	private String nome;

	@Column(nullable = true)
	private String local;

	@Column(nullable = true)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data;

	@Column(nullable = true)
	private String horaInicio;

	@Column(nullable = true)
	private String horaTermino;

	@Column(nullable = true)
	private int capacidade;

	@Column(nullable = true)
	private boolean status;
	
	@OneToMany
	private List<ClienteEntity> clientes;

}
package br.com.cng12.clubdance.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
@Table(name = "cliente")
@Entity
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 1, max = 40, message = "Entre 1 e 40 caracteres.")
	@Column(nullable = true)
	private String nome;

	@Size(min = 1, max = 15, message = "Entre 1 e 15 caracteres.")
	@Column(nullable = true)
	private String cpf;
	
	@NotNull(message = "Campo obrigatório.")
	@Column(nullable = true)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataNascimento;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private EventoEntity eventoEntity;
	
	@Column(nullable = true)
	private String tipoIngresso; 
	
}

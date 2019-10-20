package br.com.cng12.clubdance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

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

	@Size(min = 1, max = 15, message = "Entre 1 e 20 caracteres.")
	@Column(nullable = true)
	private String cpf;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private EventoEntity eventoEntity;
	
	@Column(nullable = true)
	private String tipoIngresso; 
	
}

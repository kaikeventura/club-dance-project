package br.com.cng12.clubdance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "comanda")
@Entity
public class ComandaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ClienteEntity clienteEntity;
	
	@ManyToOne
	private EventoEntity eventoEntity;
	
	@Column(nullable = true)
	private Double precoIngresso;
	
}

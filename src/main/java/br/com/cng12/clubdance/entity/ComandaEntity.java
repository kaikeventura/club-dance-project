package br.com.cng12.clubdance.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.Data;

@Data
@Table(name = "comanda")
@Entity
public class ComandaEntity implements Serializable {

	private static final long serialVersionUID = -2591534532574773462L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ClienteEntity clienteEntity;
	
	@ManyToOne
	private EventoEntity eventoEntity;
	
	@Column(nullable = true)
	@NumberFormat(style = Style.CURRENCY, pattern = "###,###,###,##0.00")
	private Double precoIngresso;
	
	@Column(nullable = true)
	private String status;
	
}

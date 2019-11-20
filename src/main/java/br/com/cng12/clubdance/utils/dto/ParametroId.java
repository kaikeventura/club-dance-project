package br.com.cng12.clubdance.utils.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class ParametroId {

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataInicio;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataFim;
	
	private String idEvento;
	
	private String idProduto;
}

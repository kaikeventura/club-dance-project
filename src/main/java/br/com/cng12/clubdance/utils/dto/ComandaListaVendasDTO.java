package br.com.cng12.clubdance.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComandaListaVendasDTO {

	private String nomeCliente;
	private String cpfCliente;
	private String nomeEvento;
	private Long idComanda;
}

package br.com.cng12.clubdance.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscalAux {

	private String nomeProduto;
	private Double valorUnitario;
	private int qtde;
}

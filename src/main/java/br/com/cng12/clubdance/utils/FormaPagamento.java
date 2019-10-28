package br.com.cng12.clubdance.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormaPagamento {

	CREDITO("CREDITO"),
	DEBITO("DEBITO"),
	DINHEIRO("DINHEIRO");
	
	private String formaPagamento;
}

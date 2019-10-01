package br.com.cng12.clubdance.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnidadeMedida {

	PACOTE("PCT"),
	PEÇA("PC"),
	METRO("MT"),
	KILO("KG"),
	LITRO("LT");
	
	private String unidade;	
	
}

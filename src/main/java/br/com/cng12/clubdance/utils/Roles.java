package br.com.cng12.clubdance.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {

	ADMINISTRADOR("ADMINISTRADOR(a)"),
	RECEPCIONISTA("RECEPCIONISTA"),
	VENDEDOR("VENDEDOR(a) BAR"),
	CAIXA("CAIXA"),
	ESTOQUISTA("ESTOQUISTA");
	
	private String role;
	
}

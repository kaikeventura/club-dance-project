package br.com.cng12.clubdance.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

	ATIVO("ATIVO"),
	INATIVO("INATIVO");
	
	private String status;
}

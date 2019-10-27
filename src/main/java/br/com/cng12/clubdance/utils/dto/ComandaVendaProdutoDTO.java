package br.com.cng12.clubdance.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComandaVendaProdutoDTO {

	private String nomeProduto;
	private int qtde;
	
}

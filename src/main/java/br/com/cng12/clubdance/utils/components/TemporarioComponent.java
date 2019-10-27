package br.com.cng12.clubdance.utils.components;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class TemporarioComponent {
	
	private Long idFornecedorTemp;
	private Long idNotaFiscalTemp;
	private Long idEventoTemp;
	private Long idClienteTemp;
	private Long idComandaProdutoEntityTemp;
	private Long idEventoTempCaixa;
	private Long idClienteTempCaixa;
	
}

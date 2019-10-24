package br.com.cng12.clubdance.utils.auxiliares;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscalAux {

	private String nomeProduto;
	@NumberFormat(style = Style.CURRENCY, pattern = "#,###,###,##0.00")
	private BigDecimal valorUnitario;
	private int qtde;
}

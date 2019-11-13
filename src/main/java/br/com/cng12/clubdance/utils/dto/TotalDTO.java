package br.com.cng12.clubdance.utils.dto;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.Data;

@Data
public class TotalDTO {

	@NumberFormat(style = Style.CURRENCY, pattern = "###,###,###,##0.00")
	private Double totalComanda;
	
	@NumberFormat(style = Style.CURRENCY, pattern = "###,###,###,##0.00")
	private Double total;
}

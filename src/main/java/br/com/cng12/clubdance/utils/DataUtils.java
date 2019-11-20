package br.com.cng12.clubdance.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

	public static String getDataHoje() {
		
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");    
		Date data = new Date();
		String dataHoje = fmt.format(data);
		
		return dataHoje;
	}
}

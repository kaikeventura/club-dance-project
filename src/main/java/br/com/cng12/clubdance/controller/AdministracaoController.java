package br.com.cng12.clubdance.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.cng12.clubdance.service.impl.EventoServiceImpl;
import br.com.cng12.clubdance.service.impl.ProdutoServiceImpl;
import br.com.cng12.clubdance.utils.DataUtils;
import br.com.cng12.clubdance.utils.dto.PeriodoRelatorio;
import br.com.cng12.clubdance.utils.dto.ParametroId;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class AdministracaoController {

	private static final String INICIO_ADM = "/administracao/inicio";
	private static final String RELATORIO_EVENTOS_FINALIZADOS = "/administracao/eventos-finalizados";
	private static final String RELATORIO_CAIXA_GERAL = "/administracao/caixa-geral";
	private static final String RELATORIO_CAIXA_POR_EVENTO = "/administracao/caixa-por-evento";
	private static final String RELATORIO_CAIXA_POR_PRODUTO = "/administracao/caixa-por-produto";
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private EventoServiceImpl eventoService;
	
	@Autowired
	private ProdutoServiceImpl produtoService;
	
	@GetMapping(INICIO_ADM)
	public String inicioRelatorios(PeriodoRelatorio periodoRelatorio, ParametroId parametroId, ModelMap model) {
		
		model.addAttribute("eventos", eventoService.listar());
		model.addAttribute("produtos", produtoService.listar());
		
		return "administracao/administracao";
	}
	
	@PostMapping(RELATORIO_EVENTOS_FINALIZADOS)
	public void relatorioEventosFinalizados(@Valid PeriodoRelatorio periodoRelatorio, HttpServletResponse response) throws JRException, SQLException, IOException {
		
		Map<String, Object> parametros = new HashMap<>();
		
		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), 
				LocalTime.of(0, 0, 0)).atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), 
				LocalTime.of(0, 0, 0)).atZone(ZoneId.systemDefault()).toInstant());
		
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		
		// Pega o arquivo .jasper localizado em resources
		InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/relatorio_eventos_inativos.jasper");
		
		// Cria o objeto JaperReport com o Stream do arquivo jasper
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		
		// Passa para o JasperPrint o relatório, os parâmetros e a fonte dos dados, no caso uma conexão ao banco de dados
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());
		
		// Configura a respota para o tipo PDF
		response.setContentType("application/pdf");
		
		// Define que o arquivo pode ser visualizado no navegador e também nome final do arquivo
		// para fazer download do relatório troque 'inline' por 'attachment'
		response.setHeader("Content-Disposition", "attachment; filename=relatorio_eventos_encerrados_"+DataUtils.getDataHoje()+".pdf");
		
		// Faz a exportação do relatório para o HttpServletResponse
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
	
	@PostMapping(RELATORIO_CAIXA_GERAL)
	public void relatorioCaixaGeral(@Valid PeriodoRelatorio periodoRelatorio, HttpServletResponse response) throws JRException, SQLException, IOException {
		
		Map<String, Object> parametros = new HashMap<>();
		
		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), 
				LocalTime.of(0, 0, 0)).atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), 
				LocalTime.of(0, 0, 0)).atZone(ZoneId.systemDefault()).toInstant());
		
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		
		InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/relatorio_caixa_geral.jasper");		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());
		
		response.setContentType("application/pdf");		
		response.setHeader("Content-Disposition", "attachment; filename=relatorio_caixa_geral_"+DataUtils.getDataHoje()+".pdf");
		
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
	
	@PostMapping(RELATORIO_CAIXA_POR_EVENTO)
	public void relatorioCaixaPorEvento(@Valid ParametroId parametroId, HttpServletResponse response) throws JRException, SQLException, IOException {
		
		Map<String, Object> parametros = new HashMap<>();
		
		Long idEvento = Long.parseLong(parametroId.getIdEvento());
		
		parametros.put("id_evento", idEvento);
		
		InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/relatorio_caixa_por_evento.jasper");		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());
		
		response.setContentType("application/pdf");		
		response.setHeader("Content-Disposition", "attachment; filename=relatorio_caixa_por_evento_"+DataUtils.getDataHoje()+".pdf");
		
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
	
	@PostMapping(RELATORIO_CAIXA_POR_PRODUTO)
	public void relatorioCaixaPorProduto(@Valid ParametroId parametroId, HttpServletResponse response) throws JRException, SQLException, IOException {
		
		Map<String, Object> parametros = new HashMap<>();
		
		Long idEvento = Long.parseLong(parametroId.getIdEvento());
		Long idProduto = Long.parseLong(parametroId.getIdProduto());
		
		parametros.put("id_evento", idEvento);
		parametros.put("id_produto", idProduto);
		
		InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/relatorio_caixa_por_produto.jasper");		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());
		
		response.setContentType("application/pdf");		
		response.setHeader("Content-Disposition", "attachment; filename=relatorio_caixa_por_produto_"+DataUtils.getDataHoje()+".pdf");
		
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
	
}

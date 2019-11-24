package br.com.cng12.clubdance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		
		//EventoController
		.antMatchers(HttpMethod.GET, "/evento/cadastrar-evento").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/evento/cadastrar-evento").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/evento/eventos").hasAnyRole("ADMIN","RECEP")
		.antMatchers(HttpMethod.GET, "/evento/editar-evento/{id}").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/evento/editar-evento").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/evento/excluir-evento/{id}").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/evento/vender-ingresso/{id}").hasAnyRole("ADMIN","RECEP")
		.antMatchers(HttpMethod.POST, "/evento/vender-ingresso/{id}").hasAnyRole("ADMIN","RECEP")
		.antMatchers(HttpMethod.GET, "/evento/buscar/nome").hasAnyRole("ADMIN","RECEP")
		
		//ClienteController
		.antMatchers(HttpMethod.GET, "/evento/venda/editar-venda/{id}").hasAnyRole("ADMIN","RECEP")
		.antMatchers(HttpMethod.POST, "/evento/venda/editar-venda").hasAnyRole("ADMIN","RECEP")
		.antMatchers(HttpMethod.GET, "/evento/venda/buscar/cpf").hasAnyRole("ADMIN","RECEP")
		
		//BarController
		.antMatchers(HttpMethod.GET, "/bar/inicio").hasAnyRole("ADMIN","BAR")
		.antMatchers(HttpMethod.GET, "/bar/vender/selecionar-evento").hasAnyRole("ADMIN","BAR")
		.antMatchers(HttpMethod.GET, "/bar/vender/selecionar-evento").hasAnyRole("ADMIN","BAR")
		.antMatchers(HttpMethod.GET, "/bar/vender/selecionar-cliente/{id}").hasAnyRole("ADMIN","BAR")
		.antMatchers(HttpMethod.POST, "/bar/vender/selecionar-produto").hasAnyRole("ADMIN","BAR")
		.antMatchers(HttpMethod.GET, "/bar/vender/quantidade-produto").hasAnyRole("ADMIN","BAR")
		.antMatchers(HttpMethod.GET, "/bar/vender/editar/selecionar-cliente").hasAnyRole("ADMIN")
		
		//EstoqueController 
		.antMatchers(HttpMethod.GET, "/estoque/inicio-estoque").hasAnyRole("ADMIN","ESTOQ")
		
		//ProdutoController
		.antMatchers(HttpMethod.GET, "/estoque/produto/cadastrar-produto").hasAnyRole("ADMIN","ESTOQ")
		.antMatchers(HttpMethod.POST, "/estoque/produto/cadastrar-produto").hasAnyRole("ADMIN","ESTOQ")
		.antMatchers(HttpMethod.GET, "/estoque/produto/produtos").hasAnyRole("ADMIN","ESTOQ")
		.antMatchers(HttpMethod.GET, "/estoque/produto/editar-produto/{id}").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/estoque/produto/editar-produto").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/estoque/produto/excluir-produto/{id}").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/estoque/produto/buscar/nome").hasAnyRole("ADMIN","ESTOQ")
		
		//FornecedorController
		.antMatchers(HttpMethod.GET, "/estoque/fornecedor/cadastrar-fornecedor").hasAnyRole("ADMIN","ESTOQ")
		.antMatchers(HttpMethod.POST, "/estoque/fornecedor/cadastrar-fornecedor").hasAnyRole("ADMIN","ESTOQ")
		.antMatchers(HttpMethod.GET, "/estoque/fornecedor/fornecedores").hasAnyRole("ADMIN","ESTOQ")
		.antMatchers(HttpMethod.GET, "/estoque/fornecedor/detalhes/fornecedor-detalhes/{id}").hasAnyRole("ADMIN","ESTOQ")
		.antMatchers(HttpMethod.GET, "/estoque/fornecedor/detalhes/fornecedor-detalhes/editar/{id}").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/estoque/fornecedor/detalhes/fornecedor-detalhes/editar-fornecedor").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/estoque/fornecedor/detalhes/fornecedor-detalhes/excluir/{id}").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/estoque/fornecedor/buscar/nome").hasAnyRole("ADMIN","ESTOQ")
		
		//NotaFiscalController
		.antMatchers(HttpMethod.GET, "/estoque/nota-fiscal/selecionar-fornecedor").hasAnyRole("ADMIN","ESTOQ")
		.antMatchers(HttpMethod.GET, "/estoque/nota-fiscal/buscar-fornecedor/nome").hasAnyRole("ADMIN","ESTOQ")
		.antMatchers(HttpMethod.GET, "/estoque/nota-fiscal/lancar/nota-fiscal/{id}").hasAnyRole("ADMIN","ESTOQ")
		.antMatchers(HttpMethod.POST, "/estoque/nota-fiscal/lancar/nota-fiscal").hasAnyRole("ADMIN","ESTOQ")
		.antMatchers(HttpMethod.GET, "/estoque/nota-fiscal/lancar/lancar-produto").hasAnyRole("ADMIN","ESTOQ")
		.antMatchers(HttpMethod.POST, "/estoque/nota-fiscal/lancar/lancar-produto").hasAnyRole("ADMIN","ESTOQ")
		.antMatchers(HttpMethod.GET, "/estoque/nota-fiscal/lista/notas-fiscais").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/estoque/nota-fiscal/lista/editar/selecionar-produto/{id}").hasAnyRole("ADMIN")
		
		//CaixaController
		.antMatchers(HttpMethod.GET, "/caixa/inicio-caixa").hasAnyRole("ADMIN","CAIXA")
		.antMatchers(HttpMethod.GET, "/caixa/cobranca/selecionar-evento").hasAnyRole("ADMIN","CAIXA")
		.antMatchers(HttpMethod.GET, "/caixa/cobranca/selecionar-evento/{id}").hasAnyRole("ADMIN","CAIXA")
		.antMatchers(HttpMethod.GET, "/caixa/cobranca/cliente/selecionar-cliente/{id}").hasAnyRole("ADMIN","CAIXA")
		.antMatchers(HttpMethod.GET, "/cielo/cielo-pagamentos").hasAnyRole("ADMIN","CAIXA")
		.antMatchers(HttpMethod.POST, "/cielo/cielo-pagamentos/processa-pagamento").hasAnyRole("ADMIN","CAIXA")
		.antMatchers(HttpMethod.POST, "/caixa/cobranca/pagamentos/dinheiro").hasAnyRole("ADMIN","CAIXA")
		.antMatchers(HttpMethod.GET, "/cielo/cielo-pagamentos/aprovado").hasAnyRole("ADMIN","CAIXA")
		
		//AdministracaoController
		.antMatchers(HttpMethod.GET, "/administracao/inicio").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/administracao/eventos-finalizados").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/administracao/caixa-geral").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/administracao/caixa-por-evento").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/administracao/caixa-por-produto").hasAnyRole("ADMIN")
		
		//GeradorDeCartoesController
		.antMatchers(HttpMethod.GET, "/cartoes").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/cartoes/gerador-cartoes/gerar-cartao-credito").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/cartoes/gerador-cartoes/gerar-cartao-debito").hasAnyRole("ADMIN")
		
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/relatorios/**","/css/**","/img/**","/js/**");		
	}
}

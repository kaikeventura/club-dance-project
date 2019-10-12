package br.com.cng12.clubdance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "fornecedor")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FornecedorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true)
	private String nomeFantasia;
	
	@Column(nullable = false, unique = true)
	private String razaoSocial;
	
	@Column(nullable = false)
	private String atividadePrincipal;
	
	@Column(nullable = false, unique = true)
	private String cnpj;
	
	@Column(nullable = false, unique = true)
	private String inscricaoEstadual;
	
	@Column(nullable = true)
	private String endereco;
	
	@Column(nullable = true)
	private String bairro;
	
	@Column(nullable = true)
	private String cidade;
	
	@Column(nullable = true)
	private String uf;
	
	@Column(nullable = true)
	private String cep;
	
	@Column(nullable = true)
	private String telefone;
	
	@Column(nullable = false)
	private String celular;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String nomeContato;
	
	@Column(nullable = true, unique = true)
	private String status;
}

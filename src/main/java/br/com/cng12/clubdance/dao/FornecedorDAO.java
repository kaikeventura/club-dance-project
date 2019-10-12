package br.com.cng12.clubdance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cng12.clubdance.entity.FornecedorEntity;

@Repository
public interface FornecedorDAO extends JpaRepository<FornecedorEntity, Long> {

	@Transactional
	@Modifying
	@Query("UPDATE FornecedorEntity f SET f.nomeFantasia =:nomeFantasia, f.razaoSocial =:razaoSocial, f.atividadePrincipal =:atividadePrincipal,"
			+ "f.cnpj =:cnpj, f.inscricaoEstadual =:inscricaoEstadual, f.endereco =:endereco, f.bairro =:bairro, f.cidade =:cidade,"
			+ "f.uf =:uf, f.cep =:cep, f.telefone =:telefone, f.celular =:celular, f.email =:email, f.nomeContato =:nomeContato,"
			+ "f.status =:status WHERE f.id =:id")
	void editar(@Param("nomeFantasia") String nomeFantasia, @Param("razaoSocial") String razaoSocial,
			@Param("atividadePrincipal") String atividadePrincipal, @Param("cnpj") String cnpj,
			@Param("inscricaoEstadual") String inscricaoEstadual, @Param("endereco") String endereco,
			@Param("bairro") String bairro, @Param("cidade") String cidade, @Param("uf") String uf,
			@Param("cep") String cep, @Param("telefone") String telefone, @Param("celular") String celular,
			@Param("email") String email, @Param("nomeContato") String nomeContato, @Param("status") String status,
			@Param("id") Long id);

}

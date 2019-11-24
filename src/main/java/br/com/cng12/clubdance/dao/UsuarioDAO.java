package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cng12.clubdance.entity.UsuarioEntity;

@Repository
public interface UsuarioDAO extends JpaRepository<UsuarioEntity, String> {

	UsuarioEntity findByLogin(String login);
	
	@Query("select u from UsuarioEntity u where u.usuario_id =:usuario_id")
	UsuarioEntity buscarPorId(@Param("usuario_id") Long id);
	
	@Query("select u from UsuarioEntity u where u.status = 'ATIVO'")
	List<UsuarioEntity> listarUsuariosAtivos();
	
	@Query("select u from UsuarioEntity u where u.status = 'INATIVO'")
	List<UsuarioEntity> listarUsuariosInativos();
	
	@Transactional
	@Modifying
	@Query("UPDATE UsuarioEntity u SET u.status =:status WHERE u.id =:id")
	void alterarStatusParaInativo(@Param("status") String status,
			@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE UsuarioEntity u SET u.status =:status WHERE u.id =:id")
	void alterarStatusParaAtivo(@Param("status") String status,
			@Param("id") Long id);
	
}
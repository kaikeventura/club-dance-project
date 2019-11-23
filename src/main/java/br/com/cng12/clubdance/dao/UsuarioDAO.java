package br.com.cng12.clubdance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cng12.clubdance.entity.UsuarioEntity;

@Repository
public interface UsuarioDAO extends JpaRepository<UsuarioEntity, String> {

	UsuarioEntity findByLogin(String login);
}

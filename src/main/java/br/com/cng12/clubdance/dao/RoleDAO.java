package br.com.cng12.clubdance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cng12.clubdance.entity.RoleEntity;

@Repository
public interface RoleDAO extends JpaRepository<RoleEntity, String> {

	RoleEntity findByNomeRole(String nomeRole);
}

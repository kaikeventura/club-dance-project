package br.com.cng12.clubdance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cng12.clubdance.entity.ComandaEntity;

@Repository
public interface ComandaDAO extends JpaRepository<ComandaEntity, Long>{

}

package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cng12.clubdance.entity.EventoEntity;

@Repository
public interface EventoDAO extends JpaRepository<EventoEntity, Long> {

	@Query("select e from EventoEntity e where e.nome like concat('%',?1,'%')")
	List<EventoEntity> buscarEventoPorNome(String nome);
}

package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cng12.clubdance.entity.EventoEntity;

@Repository
public interface EventoDAO extends JpaRepository<EventoEntity, Long> {

	@Query("select e from EventoEntity e where e.nome like concat('%',?1,'%')")
	List<EventoEntity> buscarEventoPorNome(String nome);
	
	@Transactional
	@Modifying
	@Query("UPDATE EventoEntity e SET e.capacidade =:capacidade WHERE e.id =:id")
	void editarCapacidade(@Param("capacidade") int capacidade, @Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE EventoEntity e SET e.capacidadeCamarote =:capacidadeCamarote WHERE e.id =:id")
	void editarCapacidadeCamarote(@Param("capacidadeCamarote") int capacidade, @Param("id") Long id);
	
	@Query("select e from EventoEntity e where e.status = 'ATIVO'")
	List<EventoEntity> listarEventosAtivos();
	
	@Query("select e from EventoEntity e where e.status = 'INATIVO'")
	List<EventoEntity> listarEventosInativos();
}

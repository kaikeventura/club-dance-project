package br.com.cng12.clubdance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cng12.clubdance.entity.ProdutoEntity;

@Repository
public interface ProdutoDAO extends JpaRepository<ProdutoEntity, Long> {

	@Transactional
	@Modifying
	@Query("UPDATE ProdutoEntity p SET p.nome =:nome, p.marca =:marca, p.unidadeMedida =:unidadeMedida, "
			+ "p.margemLucro =:margemLucro, p.status =:status WHERE p.id =:id")
	void editarProduto(@Param("nome") String nome, @Param("marca") String marca,
			@Param("unidadeMedida") String unidadeMedida, @Param("margemLucro") Double margemLucro,
			@Param("status") String status, @Param("id") Long id);

	@Query("select p from ProdutoEntity p where p.nome like concat('%',?1,'%')")
	List<ProdutoEntity> buscarProdutoPorNome(String nome);

	@Query("select p from ProdutoEntity p where p.status = 'ATIVO'")
	List<ProdutoEntity> listarProdutosAtivos();

	@Transactional
	@Modifying
	@Query("UPDATE ProdutoEntity p SET p.preco =:preco, p.qtdeEstoque =:qtdeEstoque WHERE p.id =:id")
	void lancarEntradaDeProduto(@Param("preco") Double preco, @Param("qtdeEstoque") int qtdeEstoque,
			@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE ProdutoEntity p SET p.qtdeEstoque =:qtdeEstoque WHERE p.id =:id")
	void retirarQtdeEstoque(@Param("qtdeEstoque") int qtdeEstoque,
			@Param("id") Long id);

}

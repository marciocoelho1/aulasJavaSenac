package br.com.senac.loja.repository;

import br.com.senac.loja.model.Produto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Override
    @EntityGraph(attributePaths = "categoria")
    List<Produto> findAll(Sort sort);

    @Query("select p from Produto p join fetch p.categoria where p.id = :id")
    Optional<Produto> buscarComCategoria(@Param("id") Long id);

    boolean existsByCategoriaId(Long categoriaId);
}

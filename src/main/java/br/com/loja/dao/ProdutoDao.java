package br.com.loja.dao;

import br.com.loja.model.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoDao {

    public final JdbcTemplate jdbcTemplate;

    public ProdutoDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserir(Produto produto){
        String sql = """
                INSERT INTO produto (nome, preco)
                VALUES (?, ?)
                """;

        jdbcTemplate.update(sql, produto.getNome(),
                produto.getPreco());
    }

    public List<Produto> listar(){
        String sql = """
                SELECT id, nome, preco
                FROM produto
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Produto produto = new Produto();
            produto.setId(rs.getLong("id"));
            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getBigDecimal("preco"));
            return produto;
        });
    }

    public boolean atualizar(Long id, Produto produto){
        String sql = """
                UPDATE produto
                SET nome = ?, preco = ?
                WHERE id = ?
                """;

        int linhasAfetadas = jdbcTemplate.update(sql, produto.getNome(),
                produto.getPreco(), id);
        return linhasAfetadas > 0;
    }

    public boolean excluir(Long id){
        String sql = """
                DELETE FROM produto
                WHERE id = ?
                """;

        int linhasAfetadas = jdbcTemplate.update(sql, id);
        return linhasAfetadas > 0;
    }
}

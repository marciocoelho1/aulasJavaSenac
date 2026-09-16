package br.com.loja.dao;

import br.com.loja.model.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}

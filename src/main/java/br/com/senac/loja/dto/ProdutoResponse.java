package br.com.senac.loja.dto;

import br.com.senac.loja.model.Produto;

import java.math.BigDecimal;

public record ProdutoResponse(

        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Integer quantidade,
        CategoriaResponse categoria
) {

    public static ProdutoResponse from (Produto produto){
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidade(),
                CategoriaResponse.from(produto.getCategoria())
        );
    }
}

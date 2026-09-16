package br.com.loja.model;

import java.math.BigDecimal;

public class Produto {

    private String nome;
    private BigDecimal preco;

    public Produto(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}

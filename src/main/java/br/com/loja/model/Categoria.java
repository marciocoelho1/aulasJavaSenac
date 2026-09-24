package br.com.loja.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name = "categorias",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_categoria_nome",
                columnNames = "nome"
        )
        }
)

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String descricao;

    @Column(nullable = false, length = 80)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos = new ArrayList<>();

    public Categoria(){}
    public Categoria(String descricao, String nome) {
        this.descricao = descricao;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}

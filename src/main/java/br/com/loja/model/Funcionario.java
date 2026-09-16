package br.com.loja.model;

public class Funcionario {

    private Integer id;
    private String nome;
    private String cargo;
    private String tempoEmpresa;
    private String ferias;

    public Funcionario() {
    }

    public Funcionario(Integer id, String nome, String cargo, String tempoEmpresa, String ferias) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.tempoEmpresa = tempoEmpresa;
        this.ferias = ferias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTempoEmpresa() {
        return tempoEmpresa;
    }

    public void setTempoEmpresa(String tempoEmpresa) {
        this.tempoEmpresa = tempoEmpresa;
    }

    public String getFerias() {
        return ferias;
    }

    public void setFerias(String ferias) {
        this.ferias = ferias;
    }
}
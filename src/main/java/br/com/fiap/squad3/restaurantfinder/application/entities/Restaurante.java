package br.com.fiap.squad3.restaurantfinder.application.entities;

public class Restaurante {
    private final int CAPACIDADE_MINIMA = 1;
    private String nome;
    private String tipoCozinha;
    private Integer capacidade;
    private Localizacao localizacao;
    private Funcionamento funcionamento;

    public Restaurante(
            String nome,
            String tipoCozinha,
            Integer capacidade
    ) {
        this.nome = nome;
        this.tipoCozinha = tipoCozinha;
        this.capacidade = capacidade;
        this.localizacao = null;
        this.funcionamento = null;
    }

    public Restaurante(
            String nome,
            String tipoCozinha,
            Integer capacidade,
            Localizacao localizacao,
            Funcionamento funcionamento
    ) {
        this.nome = nome;
        this.tipoCozinha = tipoCozinha;
        this.capacidade = capacidade;
        this.localizacao = localizacao;
        this.funcionamento = funcionamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome deve ser informado.");
        }

        this.nome = nome;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        if (tipoCozinha == null || tipoCozinha.isBlank()) {
            throw new IllegalArgumentException("Tipo de cozinha deve ser informada.");
        }

        this.tipoCozinha = tipoCozinha;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        if (capacidade < CAPACIDADE_MINIMA) {
            throw new IllegalArgumentException("Capacidade mínima é " + CAPACIDADE_MINIMA + ".");
        }

        this.capacidade = capacidade;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Funcionamento getFuncionamento() {
        return funcionamento;
    }

    public void setFuncionamento(Funcionamento funcionamento) {
        this.funcionamento = funcionamento;
    }
}

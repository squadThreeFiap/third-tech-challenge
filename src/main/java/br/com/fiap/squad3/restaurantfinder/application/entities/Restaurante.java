package br.com.fiap.squad3.restaurantfinder.application.entities;

public class Restaurante {
    public static final int CAPACIDADE_MINIMA = 1;

    private Long id;
    private String nome;
    private String tipoCozinha;
    private Integer capacidade;
    private Localizacao localizacao;
    private Funcionamento funcionamento;

    public Restaurante(
            Long id,
            String nome,
            String tipoCozinha,
            Integer capacidade
    ) {
        this.id = id;
        this.setNome(nome);
        this.setTipoCozinha(tipoCozinha);
        this.setCapacidade(capacidade);
        this.localizacao = null;
        this.funcionamento = null;
    }

    public Restaurante(
            Long id,
            String nome,
            String tipoCozinha,
            Integer capacidade,
            Localizacao localizacao,
            Funcionamento funcionamento
    ) {
        this.id = id;
        this.setNome(nome);
        this.setTipoCozinha(tipoCozinha);
        this.setCapacidade(capacidade);
        this.localizacao = localizacao;
        this.funcionamento = funcionamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

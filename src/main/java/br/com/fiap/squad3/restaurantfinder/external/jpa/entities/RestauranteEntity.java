package br.com.fiap.squad3.restaurantfinder.external.jpa.entities;

import br.com.fiap.squad3.restaurantfinder.application.entities.enums.DiaSemana;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurantes")
public class RestauranteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String tipoCozinha;

    @NotNull
    private String cep;

    @NotNull
    private String endereco;

    @NotNull
    private String uf;

    @NotNull
    private String cidade;

    @NotNull
    private String bairro;

    private Integer numero;

    @Enumerated(EnumType.STRING)
    private Set<DiaSemana> diasFuncionamentos;

    @NotNull
    private LocalTime horaAbertura;

    @NotNull
    private LocalTime horaEnceramento;

    @NotNull
    private int capacidade;

    public RestauranteEntity(
            String nome,
            String tipoCozinha,
            String cep,
            String endereco,
            String uf,
            String cidade,
            String bairro,
            Set<DiaSemana> diasFuncionamentos,
            LocalTime horaAbertura,
            LocalTime horaEnceramento,
            int capacidade
    ) {
        this.nome = nome;
        this.tipoCozinha = tipoCozinha;
        this.cep = cep;
        this.endereco = endereco;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.diasFuncionamentos = diasFuncionamentos;
        this.horaAbertura = horaAbertura;
        this.horaEnceramento = horaEnceramento;
        this.capacidade = capacidade;
    }
}
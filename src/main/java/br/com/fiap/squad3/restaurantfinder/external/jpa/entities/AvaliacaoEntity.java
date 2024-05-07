package br.com.fiap.squad3.restaurantfinder.external.jpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "avaliacoes")
public class AvaliacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String comentario;

    @NotNull
    private int nota;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restauranteEntity;
}
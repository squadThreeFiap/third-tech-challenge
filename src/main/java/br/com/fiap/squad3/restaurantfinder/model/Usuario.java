package br.com.fiap.squad3.restaurantfinder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String cpf;

    @NotNull
    private String nome;

    @NotNull
    private String ddd;

    @NotNull
    private String telefone;

    @NotNull
    private String email;

    @NotNull
    private LocalDate dataCadastro;

    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDate.now();
    }
}
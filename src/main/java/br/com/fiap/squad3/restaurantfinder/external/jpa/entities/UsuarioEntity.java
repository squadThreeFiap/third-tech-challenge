package br.com.fiap.squad3.restaurantfinder.external.jpa.entities;

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
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, length = 11)
    private String cpf;

    @NotNull
    @Column(length = 100)
    private String nome;

    @NotNull
    @Column(length = 2)
    private String ddd;

    @NotNull
    @Column(length = 9)
    private String telefone;

    @NotNull
    private String email;

    @NotNull
    private LocalDate dataCadastro;

    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDate.now();
    }

    public UsuarioEntity(Long id) {
        this.id = id;
    }
}
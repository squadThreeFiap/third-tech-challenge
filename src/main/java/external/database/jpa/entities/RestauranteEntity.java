package external.database.jpa.entities;

import application.entities.enums.DiaSemana;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.List;

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
    @Column(unique = true)
    private String cnpjCpf;

    @NotNull
    private String nomeFantasia;

    @NotNull
    private String ddd;

    @NotNull
    private String telefoneContato;

    @NotNull
    private String emailContato;

    @NotNull
    private String culinaria;

    @NotNull
    private String categoria;

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

    @Enumerated(EnumType.STRING)
    private List<DiaSemana> diasFuncionamentos;

    @NotNull
    private LocalTime horaAbertura;

    @NotNull
    private LocalTime horaEnceramento;

    @NotNull
    private int capacidade;
}
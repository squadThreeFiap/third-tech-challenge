package external.database.jpa.entities;

import application.entities.enums.StatusReserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservas")
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuarioEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restauranteEntity;

    @NotNull
    private Integer quantidadePessoas;

    @NotNull
    private LocalDateTime dataHoraInicio;

    @NotNull
    private LocalDateTime dataHoraFim;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;
}
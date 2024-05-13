package mocks;

import br.com.fiap.squad3.restaurantfinder.application.entities.ReservaDetalhada;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.StatusReserva;

import java.time.LocalDateTime;

public class ReservaDetalhadaMock extends ReservaDetalhada {
    public ReservaDetalhadaMock() {
        super(
                1L,
                new UsuarioMock(),
                2,
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                StatusReserva.AGENDADO
        );
    }
}

package mocks;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.StatusReserva;

import java.time.LocalDateTime;

public class ReservaMock extends Reserva {
    public ReservaMock() {
        super(1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
    }

    public ReservaMock(StatusReserva statusReserva) {
        super(1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now().plusHours(2),statusReserva);
    }
}

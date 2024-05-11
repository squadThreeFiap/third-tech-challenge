package br.com.fiap.squad3.restaurantfinder.application.gateways;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaGateway {
    List<Reserva> obterTodasAsReservasOcupadasNoHorarioAgendado(
            Long idRestaurante,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim
    );

    Reserva cadastrar(Reserva reserva);
}

package br.com.fiap.squad3.restaurantfinder.application.gateways;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;

import java.time.LocalDateTime;

public interface ReservaMesaGateway {
    Boolean verificarSeEstaDisponivelParaReservar(
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim,
            Integer quantidadePessoas
    );

    Reserva cadastrar(Reserva reserva);
}

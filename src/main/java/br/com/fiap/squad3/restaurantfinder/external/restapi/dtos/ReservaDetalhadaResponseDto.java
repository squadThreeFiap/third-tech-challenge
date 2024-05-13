package br.com.fiap.squad3.restaurantfinder.external.restapi.dtos;

import br.com.fiap.squad3.restaurantfinder.application.entities.enums.StatusReserva;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public record ReservaDetalhadaResponseDto(
        Long id,

        UsuarioReservaDetalhadaDto cliente,

        int quantidadePessoas,

        LocalDateTime dataHoraInicio,

        LocalDateTime dataHoraFim,

        @Enumerated(EnumType.STRING)
        StatusReserva status
) {
}
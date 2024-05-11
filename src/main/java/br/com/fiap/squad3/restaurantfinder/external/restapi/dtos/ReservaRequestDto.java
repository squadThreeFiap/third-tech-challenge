package br.com.fiap.squad3.restaurantfinder.external.restapi.dtos;

import java.time.LocalDateTime;

public record ReservaRequestDto(
        Long idUsuario,
        Long idRestaurante,
        int quantidadePessoas,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim
) {
}
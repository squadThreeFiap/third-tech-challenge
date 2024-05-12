package br.com.fiap.squad3.restaurantfinder.external.restapi.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoRequestDto(
        @NotNull
        Long reservaId,

        @NotNull
        @Min(1)
        @Max(5)
        short nota,

        String comentario
) {
}
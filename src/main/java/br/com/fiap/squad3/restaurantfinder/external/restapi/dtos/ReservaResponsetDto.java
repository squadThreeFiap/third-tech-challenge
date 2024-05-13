package br.com.fiap.squad3.restaurantfinder.external.restapi.dtos;

import br.com.fiap.squad3.restaurantfinder.application.entities.enums.StatusReserva;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record ReservaResponsetDto(
        @Schema(hidden = true)
        Long id,
        @Enumerated(EnumType.STRING)
        StatusReserva status
) {
}
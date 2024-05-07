package br.com.fiap.squad3.restaurantfinder.external.restapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record RestauranteResponse(@Schema(hidden = true) Long id) {
}
package br.com.fiap.squad3.restaurantfinder.external.restapi.converter;

import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteRequest;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteResponse;

public interface RestauranteConverter {
    RestauranteResponse toResponse(Restaurante restaurante);

    Restaurante toDomain(RestauranteRequest restauranteRequest);

    void updateEntityFromDto(Restaurante restaurante, RestauranteRequest restauranteRequest);
}
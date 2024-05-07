package br.com.fiap.squad3.restaurantfinder.external.restapi.converter;

import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteDto;

public interface RestauranteConverter {
    RestauranteDto toDto(Restaurante restauranteEntity);

    Restaurante toEntity(RestauranteDto restauranteDto);

    void updateEntityFromDto(Restaurante restauranteEntity, RestauranteDto restauranteDto);
}
package br.com.fiap.squad3.restaurantfinder.external.jpa.converter;

import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteDto;

public interface RestauranteConverter {
    RestauranteDto toDto(RestauranteEntity restauranteEntity);

    RestauranteEntity toEntity(RestauranteDto restauranteDto);

    void updateEntityFromDto(RestauranteEntity restauranteEntity, RestauranteDto restauranteDto);
}
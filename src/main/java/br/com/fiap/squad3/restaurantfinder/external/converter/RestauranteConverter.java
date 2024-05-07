package br.com.fiap.squad3.restaurantfinder.external.converter;

import br.com.fiap.squad3.restaurantfinder.external.database.jpa.entities.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteDto;

public interface RestauranteConverter {
    RestauranteDto toDto(RestauranteEntity restauranteEntity);

    RestauranteEntity toEntity(RestauranteDto restauranteDto);

    void updateEntityFromDto(RestauranteEntity restauranteEntity, RestauranteDto restauranteDto);
}
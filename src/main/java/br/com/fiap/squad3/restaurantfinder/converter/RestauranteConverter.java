package br.com.fiap.squad3.restaurantfinder.converter;

import br.com.fiap.squad3.restaurantfinder.model.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.model.dtos.RestauranteDto;

public interface RestauranteConverter {
    RestauranteDto toDto(RestauranteEntity restauranteEntity);

    RestauranteEntity toEntity(RestauranteDto restauranteDto);

    void updateEntityFromDto(RestauranteEntity restauranteEntity, RestauranteDto restauranteDto);
}
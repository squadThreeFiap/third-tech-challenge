package br.com.fiap.squad3.restaurantfinder.converter;

import br.com.fiap.squad3.restaurantfinder.model.Restaurante;
import br.com.fiap.squad3.restaurantfinder.model.dtos.RestauranteDto;

public interface RestauranteConverter {
    RestauranteDto toDto(Restaurante restaurante);

    Restaurante toEntity(RestauranteDto restauranteDto);

    void updateEntityFromDto(Restaurante restaurante, RestauranteDto restauranteDto);
}
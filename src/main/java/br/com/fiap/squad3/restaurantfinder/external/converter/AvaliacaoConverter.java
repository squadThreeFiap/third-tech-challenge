package br.com.fiap.squad3.restaurantfinder.external.converter;

import br.com.fiap.squad3.restaurantfinder.external.database.jpa.entities.AvaliacaoEntity;
import br.com.fiap.squad3.restaurantfinder.external.database.jpa.entities.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.AvaliacaoDto;

public interface AvaliacaoConverter {
    AvaliacaoDto toDto(AvaliacaoEntity avaliacaoEntity);

    AvaliacaoEntity toEntity(AvaliacaoDto avaliacaoDto, RestauranteEntity restauranteEntity);

    void updateEntityFromDto(AvaliacaoEntity avaliacaoEntity, AvaliacaoDto avaliacaoDto);
}
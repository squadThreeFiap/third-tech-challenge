package br.com.fiap.squad3.restaurantfinder.converter;

import br.com.fiap.squad3.restaurantfinder.model.AvaliacaoEntity;
import br.com.fiap.squad3.restaurantfinder.model.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.model.dtos.AvaliacaoDto;

public interface AvaliacaoConverter {
    AvaliacaoDto toDto(AvaliacaoEntity avaliacaoEntity);

    AvaliacaoEntity toEntity(AvaliacaoDto avaliacaoDto, RestauranteEntity restauranteEntity);

    void updateEntityFromDto(AvaliacaoEntity avaliacaoEntity, AvaliacaoDto avaliacaoDto);
}
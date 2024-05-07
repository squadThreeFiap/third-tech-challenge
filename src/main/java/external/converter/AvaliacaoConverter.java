package external.converter;

import external.database.jpa.entities.AvaliacaoEntity;
import external.database.jpa.entities.RestauranteEntity;
import external.restapi.dtos.AvaliacaoDto;

public interface AvaliacaoConverter {
    AvaliacaoDto toDto(AvaliacaoEntity avaliacaoEntity);

    AvaliacaoEntity toEntity(AvaliacaoDto avaliacaoDto, RestauranteEntity restauranteEntity);

    void updateEntityFromDto(AvaliacaoEntity avaliacaoEntity, AvaliacaoDto avaliacaoDto);
}
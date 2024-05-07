package external.converter;

import external.database.jpa.entities.RestauranteEntity;
import external.restapi.dtos.RestauranteDto;

public interface RestauranteConverter {
    RestauranteDto toDto(RestauranteEntity restauranteEntity);

    RestauranteEntity toEntity(RestauranteDto restauranteDto);

    void updateEntityFromDto(RestauranteEntity restauranteEntity, RestauranteDto restauranteDto);
}
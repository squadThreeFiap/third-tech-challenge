package br.com.fiap.squad3.restaurantfinder.converter;

import external.database.jpa.entities.ReservaEntity;
import external.database.jpa.entities.RestauranteEntity;
import external.database.jpa.entities.UsuarioEntity;
import external.restapi.dtos.ReservaDto;

public interface ReservaConverter {
    ReservaDto toDto(ReservaEntity reservaEntity);

    ReservaEntity toEntity(ReservaDto reservaDto, UsuarioEntity usuarioEntity, RestauranteEntity restauranteEntity);

    void updateEntityFromDto(ReservaEntity reservaEntity, ReservaDto reservaDto);
}
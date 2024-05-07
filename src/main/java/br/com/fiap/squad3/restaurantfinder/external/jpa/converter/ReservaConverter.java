package br.com.fiap.squad3.restaurantfinder.external.jpa.converter;

import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.ReservaEntity;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.UsuarioEntity;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaDto;

public interface ReservaConverter {
    ReservaDto toDto(ReservaEntity reservaEntity);

    ReservaEntity toEntity(ReservaDto reservaDto, UsuarioEntity usuarioEntity, RestauranteEntity restauranteEntity);

    void updateEntityFromDto(ReservaEntity reservaEntity, ReservaDto reservaDto);
}
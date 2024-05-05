package br.com.fiap.squad3.restaurantfinder.converter;

import br.com.fiap.squad3.restaurantfinder.model.ReservaEntity;
import br.com.fiap.squad3.restaurantfinder.model.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.model.UsuarioEntity;
import br.com.fiap.squad3.restaurantfinder.model.dtos.ReservaDto;

public interface ReservaConverter {
    ReservaDto toDto(ReservaEntity reservaEntity);

    ReservaEntity toEntity(ReservaDto reservaDto, UsuarioEntity usuarioEntity, RestauranteEntity restauranteEntity);

    void updateEntityFromDto(ReservaEntity reservaEntity, ReservaDto reservaDto);
}
package br.com.fiap.squad3.restaurantfinder.external.converter;

import br.com.fiap.squad3.restaurantfinder.external.database.jpa.entities.UsuarioEntity;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.UsuarioDto;

public interface UsuarioConverter {
    UsuarioDto toDto(UsuarioEntity usuarioEntity);

    UsuarioEntity toEntity(UsuarioDto usuarioDto);

    void updateEntityFromDto(UsuarioEntity usuarioEntity, UsuarioDto usuarioDto);
}
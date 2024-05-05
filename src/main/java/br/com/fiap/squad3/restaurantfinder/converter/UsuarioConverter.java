package br.com.fiap.squad3.restaurantfinder.converter;

import external.database.jpa.entities.UsuarioEntity;
import external.restapi.dtos.UsuarioDto;

public interface UsuarioConverter {
    UsuarioDto toDto(UsuarioEntity usuarioEntity);

    UsuarioEntity toEntity(UsuarioDto usuarioDto);

    void updateEntityFromDto(UsuarioEntity usuarioEntity, UsuarioDto usuarioDto);
}
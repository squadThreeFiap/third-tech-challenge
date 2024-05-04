package br.com.fiap.squad3.restaurantfinder.converter;

import br.com.fiap.squad3.restaurantfinder.model.Usuario;
import br.com.fiap.squad3.restaurantfinder.model.dtos.UsuarioDto;

public interface UsuarioConverter {
    UsuarioDto toDto(Usuario usuario);

    Usuario toEntity(UsuarioDto usuarioDto);

    void updateEntityFromDto(Usuario usuario, UsuarioDto usuarioDto);
}
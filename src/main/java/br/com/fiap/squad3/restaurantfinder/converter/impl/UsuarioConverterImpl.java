package br.com.fiap.squad3.restaurantfinder.converter.impl;

import br.com.fiap.squad3.restaurantfinder.converter.UsuarioConverter;
import br.com.fiap.squad3.restaurantfinder.model.Usuario;
import br.com.fiap.squad3.restaurantfinder.model.dtos.UsuarioDto;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverterImpl implements UsuarioConverter {

    @Override
    public UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(
                usuario.getId(),
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getDdd(),
                usuario.getTelefone(),
                usuario.getEmail(),
                usuario.getDataCadastro()
        );
    }

    @Override
    public Usuario toEntity(UsuarioDto usuarioDto) {
        return new Usuario(
                usuarioDto.id(),
                usuarioDto.cpf(),
                usuarioDto.nome(),
                usuarioDto.ddd(),
                usuarioDto.telefone(),
                usuarioDto.email(),
                usuarioDto.dataCadastro()
        );
    }

    @Override
    public void updateEntityFromDto(Usuario usuario, UsuarioDto usuarioDto) {
        usuario.setNome(usuarioDto.nome());
        usuario.setDdd(usuarioDto.ddd());
        usuario.setTelefone(usuarioDto.telefone());
        usuario.setEmail(usuarioDto.email());
    }
}
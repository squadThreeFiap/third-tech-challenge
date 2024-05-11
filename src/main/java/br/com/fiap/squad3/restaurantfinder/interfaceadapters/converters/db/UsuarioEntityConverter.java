package br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db;

import br.com.fiap.squad3.restaurantfinder.application.entities.Usuario;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.UsuarioEntity;

public class UsuarioEntityConverter implements EntityConverter<Usuario, UsuarioEntity>{
    @Override
    public UsuarioEntity toEntity(Usuario usuarioDomainObj) {
        return new UsuarioEntity(
                usuarioDomainObj.getId(),
                usuarioDomainObj.getCpf(),
                usuarioDomainObj.getNome(),
                usuarioDomainObj.getDdd(),
                usuarioDomainObj.getTelefone(),
                usuarioDomainObj.getEmail(),
                usuarioDomainObj.getDataCadastro()
        );
    }

    @Override
    public Usuario toDomainObj(UsuarioEntity usuarioEntity) {
        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getCpf(),
                usuarioEntity.getNome(),
                usuarioEntity.getDdd(),
                usuarioEntity.getTelefone(),
                usuarioEntity.getEmail(),
                usuarioEntity.getDataCadastro()
        );
    }
}

package br.com.fiap.squad3.restaurantfinder.interfaceadapters.repositorygateways;

import br.com.fiap.squad3.restaurantfinder.application.entities.Usuario;
import br.com.fiap.squad3.restaurantfinder.application.gateways.UsuarioGateway;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.UsuarioEntity;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.UsuarioRepository;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db.UsuarioEntityConverter;

public class UsuarioRepositoryGateway implements UsuarioGateway {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityConverter usuarioEntityConverter;

    public UsuarioRepositoryGateway(
            UsuarioRepository usuarioRepository,
            UsuarioEntityConverter usuarioEntityConverter
    ) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioEntityConverter = usuarioEntityConverter;
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {
        UsuarioEntity usuarioEntity = this.usuarioEntityConverter.toEntity(usuario);
        UsuarioEntity usuarioEntitySalvo = this.usuarioRepository.save(usuarioEntity);
        return this.usuarioEntityConverter.toDomainObj(usuarioEntitySalvo);
    }

    @Override
    public Boolean verificarSeExistePeloCpf(String cpf) {
        return this.usuarioRepository.existsByCpf(cpf);
    }

    @Override
    public Boolean verificarSeExistePeloId(Long id) {
        return this.usuarioRepository.existsById(id);
    }
}

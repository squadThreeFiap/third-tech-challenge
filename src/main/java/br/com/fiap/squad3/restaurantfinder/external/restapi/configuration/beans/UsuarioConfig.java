package br.com.fiap.squad3.restaurantfinder.external.restapi.configuration.beans;

import br.com.fiap.squad3.restaurantfinder.application.gateways.UsuarioGateway;
import br.com.fiap.squad3.restaurantfinder.application.usecases.CadastroUsuarioUseCase;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.UsuarioRepository;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.UsuarioDtoConverter;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db.UsuarioEntityConverter;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.repositorygateways.UsuarioRepositoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {
    @Bean
    CadastroUsuarioUseCase cadastroUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new CadastroUsuarioUseCase(usuarioGateway);
    }

    @Bean
    UsuarioGateway usuarioGateway(
            UsuarioRepository usuarioRepository,
            UsuarioEntityConverter usuarioEntityConverter
    ) {
        return new UsuarioRepositoryGateway(usuarioRepository, usuarioEntityConverter);
    }

    @Bean
    UsuarioEntityConverter usuarioEntityConverter() {
        return new UsuarioEntityConverter();
    }

    @Bean
    UsuarioDtoConverter usuarioDtoConverter() {
        return new UsuarioDtoConverter();
    }
}

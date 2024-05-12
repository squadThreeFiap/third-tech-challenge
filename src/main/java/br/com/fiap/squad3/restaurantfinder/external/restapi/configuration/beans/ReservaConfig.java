package br.com.fiap.squad3.restaurantfinder.external.restapi.configuration.beans;

import br.com.fiap.squad3.restaurantfinder.application.gateways.ReservaGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.RestauranteGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.UsuarioGateway;
import br.com.fiap.squad3.restaurantfinder.application.usecases.CadastroReservaUseCase;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.ReservaRepository;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.RestauranteRepository;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.UsuarioRepository;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.ReservaDtoConverter;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db.ReservaEntityConverter;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.repositorygateways.ReservaRepositoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservaConfig {
    @Bean
    CadastroReservaUseCase reservaUseCase(
            UsuarioGateway usuarioGateway,
            RestauranteGateway restauranteGateway,
            ReservaGateway reservaGateway
    ) {
        return new CadastroReservaUseCase(usuarioGateway, restauranteGateway, reservaGateway);
    }

    @Bean
    ReservaGateway reservaGateway(
            ReservaRepository reservaRepository,
            ReservaEntityConverter reservaEntityConverter,
            UsuarioRepository usuarioRepository,
            RestauranteRepository restauranteRepository
    ) {
        return new ReservaRepositoryGateway(
                reservaRepository,
                reservaEntityConverter,
                usuarioRepository,
                restauranteRepository
        );
    }

    @Bean
    ReservaEntityConverter reservaEntityConverter() {
        return new ReservaEntityConverter();
    }

    @Bean
    ReservaDtoConverter reservaDtoConverter() {
        return new ReservaDtoConverter();
    }
}

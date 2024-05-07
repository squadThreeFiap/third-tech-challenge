package br.com.fiap.squad3.restaurantfinder.external.restapi.configuration.beans;

import br.com.fiap.squad3.restaurantfinder.application.gateways.RestauranteGateway;
import br.com.fiap.squad3.restaurantfinder.application.usecases.CadastroRestauranteUseCase;
import br.com.fiap.squad3.restaurantfinder.infrastructure.gateways.RestauranteEntityMapper;
import br.com.fiap.squad3.restaurantfinder.infrastructure.gateways.RestauranteRepositoryGateway;
import br.com.fiap.squad3.restaurantfinder.infrastructure.persistence.RestauranteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfig {
    @Bean
    CadastroRestauranteUseCase cadastroRestauranteUseCase(RestauranteGateway restauranteGateway) {
        return new CadastroRestauranteUseCase(restauranteGateway);
    }

    @Bean
    RestauranteGateway restauranteGateway(
            RestauranteRepository restauranteRepository,
            RestauranteEntityMapper restauranteEntityMapper
    ) {
        return new RestauranteRepositoryGateway(restauranteRepository, restauranteEntityMapper);
    }

    @Bean
    RestauranteEntityMapper restauranteEntityMapper() {
        return new RestauranteEntityMapper();
    }
}

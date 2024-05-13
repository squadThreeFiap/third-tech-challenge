package br.com.fiap.squad3.restaurantfinder.external.restapi.configuration.beans;

import br.com.fiap.squad3.restaurantfinder.application.gateways.ReservaGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.RestauranteGateway;
import br.com.fiap.squad3.restaurantfinder.application.usecases.CadastroRestauranteUseCase;
import br.com.fiap.squad3.restaurantfinder.application.usecases.GerenciamentoRestauranteUseCase;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.RestauranteRepository;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.RestauranteDtoConverter;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db.RestauranteEntityConverter;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.repositorygateways.RestauranteRepositoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfig {
    @Bean
    CadastroRestauranteUseCase cadastroRestauranteUseCase(RestauranteGateway restauranteGateway) {
        return new CadastroRestauranteUseCase(restauranteGateway);
    }

    @Bean
    GerenciamentoRestauranteUseCase gerenciamentoRestauranteUseCase(
            RestauranteGateway restauranteGateway,
            ReservaGateway reservaGateway
    ) {
        return new GerenciamentoRestauranteUseCase(restauranteGateway, reservaGateway);
    }

    @Bean
    RestauranteGateway restauranteGateway(
            RestauranteRepository restauranteRepository,
            RestauranteEntityConverter restauranteEntityConverter
    ) {
        return new RestauranteRepositoryGateway(restauranteRepository, restauranteEntityConverter);
    }

    @Bean
    RestauranteEntityConverter restauranteEntityConverter() {
        return new RestauranteEntityConverter();
    }

    @Bean
    RestauranteDtoConverter restauranteDtoConverter() {
        return new RestauranteDtoConverter();
    }
}

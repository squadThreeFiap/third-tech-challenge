package br.com.fiap.squad3.restaurantfinder.external.restapi.configuration.beans;

import br.com.fiap.squad3.restaurantfinder.application.gateways.AvaliacaoGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.ReservaGateway;
import br.com.fiap.squad3.restaurantfinder.application.usecases.AvaliacaoUseCase;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.AvaliacaoRepository;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.ReservaRepository;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.AvaliacaoDtoConverter;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db.AvaliacaoEntityConverter;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.repositorygateways.AvaliacaoRepositoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvaliacaoConfig {
    @Bean
    AvaliacaoUseCase avaliacaoUseCase(
            ReservaGateway reservaGateway,
            AvaliacaoGateway avaliacaoGateway
    ) {
        return new AvaliacaoUseCase(reservaGateway, avaliacaoGateway);
    }

    @Bean
    AvaliacaoGateway avaliacaoGateway(
            ReservaRepository reservaRepository,
            AvaliacaoRepository avaliacaoRepository,
            AvaliacaoEntityConverter avaliacaoEntityConverter
    ) {
        return new AvaliacaoRepositoryGateway(
                reservaRepository,
                avaliacaoRepository,
                avaliacaoEntityConverter
        );
    }

    @Bean
    AvaliacaoEntityConverter avaliacaoEntityConverter() {
        return new AvaliacaoEntityConverter();
    }

    @Bean
    AvaliacaoDtoConverter avaliacaoDtoConverter() {
        return new AvaliacaoDtoConverter();
    }
}

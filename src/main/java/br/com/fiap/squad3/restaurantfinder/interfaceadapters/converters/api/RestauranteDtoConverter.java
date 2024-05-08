package br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api;

import br.com.fiap.squad3.restaurantfinder.application.entities.Funcionamento;
import br.com.fiap.squad3.restaurantfinder.application.entities.Localizacao;
import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;

import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteRequest;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteResponse;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDtoConverter {

    public RestauranteResponse toResponse(Restaurante restaurante) {
        return new RestauranteResponse(restaurante.getId());
    }

    public Restaurante toDomain(RestauranteRequest restauranteRequest) {
        return new Restaurante(
                restauranteRequest.id(),
                restauranteRequest.nome(),
                restauranteRequest.tipoCozinha(),
                restauranteRequest.capacidade(),
                new Localizacao(
                        restauranteRequest.cep(),
                        restauranteRequest.uf(),
                        restauranteRequest.cidade(),
                        restauranteRequest.bairro(),
                        restauranteRequest.endereco(),
                        restauranteRequest.numero()
                ),
                new Funcionamento(
                        restauranteRequest.diasFuncionamentos(),
                        restauranteRequest.horaAbertura(),
                        restauranteRequest.horaEnceramento()
                )
        );
    }

    public void updateEntityFromDto(Restaurante restaurante, RestauranteRequest restauranteRequest) {
        restaurante.setNome(restauranteRequest.nome());
        restaurante.setTipoCozinha(restauranteRequest.tipoCozinha());
        restaurante.setCapacidade(restauranteRequest.capacidade());

        Localizacao localizacao = new Localizacao(
                restauranteRequest.cep(),
                restauranteRequest.uf(),
                restauranteRequest.cidade(),
                restauranteRequest.bairro(),
                restauranteRequest.endereco(),
                restauranteRequest.numero()
        );
        restaurante.setLocalizacao(localizacao);

        Funcionamento funcionamento = new Funcionamento(
                restauranteRequest.diasFuncionamentos(),
                restauranteRequest.horaAbertura(),
                restauranteRequest.horaEnceramento()
        );
        restaurante.setFuncionamento(funcionamento);
    }
}
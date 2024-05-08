package br.com.fiap.squad3.restaurantfinder.interfaceadapters.apiconverters.impl;

import br.com.fiap.squad3.restaurantfinder.application.entities.Funcionamento;
import br.com.fiap.squad3.restaurantfinder.application.entities.Localizacao;
import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.apiconverters.RestauranteConverter;

import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteRequest;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteResponse;
import org.springframework.stereotype.Component;

@Component
public class RestauranteConverterImpl implements RestauranteConverter {

    @Override
    public RestauranteResponse toResponse(Restaurante restaurante) {
        return new RestauranteResponse(restaurante.getId());
    }

    @Override
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

    @Override
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
package br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api;

import br.com.fiap.squad3.restaurantfinder.application.entities.Funcionamento;
import br.com.fiap.squad3.restaurantfinder.application.entities.Localizacao;
import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteRequestDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteResponseDto;

public class RestauranteDtoConverter implements DtoConverter<RestauranteRequestDto, Restaurante, RestauranteResponseDto> {

    @Override
    public RestauranteResponseDto toResponse(Restaurante restaurante) {
        return new RestauranteResponseDto(restaurante.getId());
    }

    @Override
    public Restaurante toDomain(RestauranteRequestDto restauranteRequestDto) {
        return new Restaurante(
                restauranteRequestDto.id(),
                restauranteRequestDto.nome(),
                restauranteRequestDto.tipoCozinha(),
                restauranteRequestDto.capacidade(),
                new Localizacao(
                        restauranteRequestDto.cep(),
                        restauranteRequestDto.uf(),
                        restauranteRequestDto.cidade(),
                        restauranteRequestDto.bairro(),
                        restauranteRequestDto.endereco(),
                        restauranteRequestDto.numero()
                ),
                new Funcionamento(
                        restauranteRequestDto.diasFuncionamentos(),
                        restauranteRequestDto.horaAbertura(),
                        restauranteRequestDto.horaEnceramento()
                )
        );
    }

    @Override
    public void updateDomainFromDto(Restaurante restaurante, RestauranteRequestDto restauranteRequestDto) {
        restaurante.setNome(restauranteRequestDto.nome());
        restaurante.setTipoCozinha(restauranteRequestDto.tipoCozinha());
        restaurante.setCapacidade(restauranteRequestDto.capacidade());

        Localizacao localizacao = new Localizacao(
                restauranteRequestDto.cep(),
                restauranteRequestDto.uf(),
                restauranteRequestDto.cidade(),
                restauranteRequestDto.bairro(),
                restauranteRequestDto.endereco(),
                restauranteRequestDto.numero()
        );
        restaurante.setLocalizacao(localizacao);

        Funcionamento funcionamento = new Funcionamento(
                restauranteRequestDto.diasFuncionamentos(),
                restauranteRequestDto.horaAbertura(),
                restauranteRequestDto.horaEnceramento()
        );
        restaurante.setFuncionamento(funcionamento);
    }
}
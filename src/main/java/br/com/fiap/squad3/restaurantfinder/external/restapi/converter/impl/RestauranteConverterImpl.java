package br.com.fiap.squad3.restaurantfinder.external.restapi.converter.impl;

import br.com.fiap.squad3.restaurantfinder.application.entities.Funcionamento;
import br.com.fiap.squad3.restaurantfinder.application.entities.Localizacao;
import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.external.restapi.converter.RestauranteConverter;

import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteDto;
import org.springframework.stereotype.Component;

@Component
public class RestauranteConverterImpl implements RestauranteConverter {

    @Override
    public RestauranteDto toDto(Restaurante restaurante) {
        return new RestauranteDto(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getLocalizacao().getNumero(),
                restaurante.getLocalizacao().getCep(),
                restaurante.getLocalizacao().getLogradouro(),
                restaurante.getLocalizacao().getUf(),
                restaurante.getLocalizacao().getCidade(),
                restaurante.getLocalizacao().getBairro(),
                restaurante.getFuncionamento().getDias(),
                restaurante.getFuncionamento().getHoraAbertura(),
                restaurante.getFuncionamento().getHoraEncerramento(),
                restaurante.getCapacidade()
        );
    }

    @Override
    public Restaurante toEntity(RestauranteDto restauranteDto) {
        return new Restaurante(
                restauranteDto.id(),
                restauranteDto.nome(),
                restauranteDto.tipoCozinha(),
                restauranteDto.capacidade(),
                new Localizacao(
                        restauranteDto.cep(),
                        restauranteDto.endereco(),
                        restauranteDto.uf(),
                        restauranteDto.cidade(),
                        restauranteDto.bairro(),
                        restauranteDto.numero()
                ),
                new Funcionamento(
                        restauranteDto.diasFuncionamentos(),
                        restauranteDto.horaAbertura(),
                        restauranteDto.horaEnceramento()
                )
        );
    }

    @Override
    public void updateEntityFromDto(Restaurante restaurante, RestauranteDto restauranteDto) {
        restaurante.setNome(restauranteDto.nome());
        restaurante.setTipoCozinha(restauranteDto.tipoCozinha());
        restaurante.setCapacidade(restauranteDto.capacidade());

        Localizacao localizacao = new Localizacao(
                restauranteDto.cep(),
                restauranteDto.uf(),
                restauranteDto.cidade(),
                restauranteDto.bairro(),
                restauranteDto.endereco(),
                restauranteDto.numero()
        );
        restaurante.setLocalizacao(localizacao);

        Funcionamento funcionamento = new Funcionamento(
                restauranteDto.diasFuncionamentos(),
                restauranteDto.horaAbertura(),
                restauranteDto.horaEnceramento()
        );
        restaurante.setFuncionamento(funcionamento);
    }
}
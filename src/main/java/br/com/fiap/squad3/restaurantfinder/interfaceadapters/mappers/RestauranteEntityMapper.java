package br.com.fiap.squad3.restaurantfinder.interfaceadapters.mappers;

import br.com.fiap.squad3.restaurantfinder.application.entities.Funcionamento;
import br.com.fiap.squad3.restaurantfinder.application.entities.Localizacao;
import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.RestauranteEntity;

public class RestauranteEntityMapper {
    public RestauranteEntity toEntity(Restaurante restauranteDomainObj) {
        return new RestauranteEntity(
                restauranteDomainObj.getNome(),
                restauranteDomainObj.getTipoCozinha(),
                restauranteDomainObj.getLocalizacao().getCep(),
                restauranteDomainObj.getLocalizacao().getLogradouro(),
                restauranteDomainObj.getLocalizacao().getUf(),
                restauranteDomainObj.getLocalizacao().getCidade(),
                restauranteDomainObj.getLocalizacao().getBairro(),
                restauranteDomainObj.getFuncionamento().getDias(),
                restauranteDomainObj.getFuncionamento().getHoraAbertura(),
                restauranteDomainObj.getFuncionamento().getHoraEncerramento(),
                restauranteDomainObj.getCapacidade()
        );
    }

    public Restaurante toDomainObj(RestauranteEntity restauranteEntity) {
        return new Restaurante(
                restauranteEntity.getId(),
                restauranteEntity.getNome(),
                restauranteEntity.getTipoCozinha(),
                restauranteEntity.getCapacidade(),
                new Localizacao(
                        restauranteEntity.getCep(),
                        restauranteEntity.getUf(),
                        restauranteEntity.getCidade(),
                        restauranteEntity.getBairro(),
                        restauranteEntity.getEndereco(),
                        restauranteEntity.getNumero()
                ),
                new Funcionamento(
                        restauranteEntity.getDiasFuncionamentos(),
                        restauranteEntity.getHoraAbertura(),
                        restauranteEntity.getHoraEnceramento()
                )
        );
    }
}

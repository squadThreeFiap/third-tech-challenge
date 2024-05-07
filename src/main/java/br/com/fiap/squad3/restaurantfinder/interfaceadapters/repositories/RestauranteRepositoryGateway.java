package br.com.fiap.squad3.restaurantfinder.interfaceadapters.repositories;

import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.application.gateways.RestauranteGateway;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.RestauranteRepository;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.mappers.RestauranteEntityMapper;

public class RestauranteRepositoryGateway implements RestauranteGateway {
    private final RestauranteRepository restauranteRepository;
    private final RestauranteEntityMapper restauranteEntityMapper;

    public RestauranteRepositoryGateway(
            RestauranteRepository restauranteRepository,
            RestauranteEntityMapper restauranteEntityMapper
    ) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteEntityMapper = restauranteEntityMapper;
    }

    @Override
    public Restaurante cadastrar(Restaurante restaurante) {
        RestauranteEntity restauranteEntity = this.restauranteEntityMapper.toEntity(restaurante);
        RestauranteEntity restauranteEntitySalvo = this.restauranteRepository.save(restauranteEntity);
        return this.restauranteEntityMapper.toDomainObj(restauranteEntitySalvo);
    }
}

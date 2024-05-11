package br.com.fiap.squad3.restaurantfinder.interfaceadapters.repositorygateways;

import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.application.gateways.RestauranteGateway;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.RestauranteRepository;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db.RestauranteEntityConverter;

public class RestauranteRepositoryGateway implements RestauranteGateway {
    private final RestauranteRepository restauranteRepository;
    private final RestauranteEntityConverter restauranteEntityConverter;

    public RestauranteRepositoryGateway(
            RestauranteRepository restauranteRepository,
            RestauranteEntityConverter restauranteEntityConverter
    ) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteEntityConverter = restauranteEntityConverter;
    }

    @Override
    public Restaurante cadastrar(Restaurante restaurante) {
        RestauranteEntity restauranteEntity = this.restauranteEntityConverter.toEntity(restaurante);
        RestauranteEntity restauranteEntitySalvo = this.restauranteRepository.save(restauranteEntity);
        return this.restauranteEntityConverter.toDomainObj(restauranteEntitySalvo);
    }

    @Override
    public Boolean verificarSeExiste(Long id) {
        return this.restauranteRepository.existsById(id);
    }

    @Override
    public Restaurante buscarPeloId(Long id) {
        RestauranteEntity restauranteEncontrado = this.restauranteRepository.getReferenceById(id);
        return this.restauranteEntityConverter.toDomainObj(restauranteEncontrado);
    }
}

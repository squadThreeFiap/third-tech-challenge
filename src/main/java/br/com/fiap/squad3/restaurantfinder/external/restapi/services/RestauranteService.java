package br.com.fiap.squad3.restaurantfinder.external.restapi.services;

import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestauranteService {
    RestauranteRequest save(RestauranteRequest restauranteRequest);

    RestauranteRequest update(Long id, RestauranteRequest restauranteRequest);

    void delete(Long id);

    Page<RestauranteRequest> findAll(Pageable pageable);

    RestauranteRequest findById(Long id);

}
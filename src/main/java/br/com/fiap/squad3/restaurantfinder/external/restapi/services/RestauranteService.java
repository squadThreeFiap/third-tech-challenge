package br.com.fiap.squad3.restaurantfinder.external.restapi.services;

import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestauranteService {
    RestauranteRequestDto save(RestauranteRequestDto restauranteRequestDto);

    RestauranteRequestDto update(Long id, RestauranteRequestDto restauranteRequestDto);

    void delete(Long id);

    Page<RestauranteRequestDto> findAll(Pageable pageable);

    RestauranteRequestDto findById(Long id);

}
package br.com.fiap.squad3.restaurantfinder.service;

import br.com.fiap.squad3.restaurantfinder.model.dtos.RestauranteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestauranteService {
    RestauranteDto save(RestauranteDto restauranteDto);

    RestauranteDto update(Long id, RestauranteDto restauranteDto);

    void delete(Long id);

    Page<RestauranteDto> findAll(Pageable pageable);

    RestauranteDto findById(Long id);

}
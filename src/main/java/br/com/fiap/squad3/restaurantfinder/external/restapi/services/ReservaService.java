package br.com.fiap.squad3.restaurantfinder.external.restapi.services;

import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservaService {
    ReservaDto save(ReservaDto reservaDto);

    ReservaDto update(Long id, ReservaDto reservaDto);

    void delete(Long id);

    ReservaDto findById(Long id);

    Page<ReservaDto> findAll(Pageable pageable);
}
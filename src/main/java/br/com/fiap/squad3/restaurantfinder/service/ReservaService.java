package br.com.fiap.squad3.restaurantfinder.service;

import external.restapi.dtos.ReservaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservaService {
    ReservaDto save(ReservaDto reservaDto);

    ReservaDto update(Long id, ReservaDto reservaDto);

    void delete(Long id);

    ReservaDto findById(Long id);

    Page<ReservaDto> findAll(Pageable pageable);
}
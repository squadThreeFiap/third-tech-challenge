package br.com.fiap.squad3.restaurantfinder.external.restapi.controllers;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.usecases.ReservaMesaUseCase;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaRequestDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaResponsetDto;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.ReservaDtoConverter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    private final ReservaMesaUseCase reservaMesaUseCase;
    private final ReservaDtoConverter reservaDtoConverter;

    public ReservaController(ReservaMesaUseCase reservaMesaUseCase, ReservaDtoConverter reservaDtoConverter) {
        this.reservaMesaUseCase = reservaMesaUseCase;
        this.reservaDtoConverter = reservaDtoConverter;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReservaResponsetDto> save(@Valid @RequestBody ReservaRequestDto reservaRequestDto) {
        Reserva reserva = reservaDtoConverter.toDomain(reservaRequestDto);
        Reserva reservaCadastrada = reservaMesaUseCase.cadastrar(reserva);
        ReservaResponsetDto reservaCadastradaResponse = reservaDtoConverter.toResponse(reservaCadastrada);

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaCadastradaResponse);
    }
}
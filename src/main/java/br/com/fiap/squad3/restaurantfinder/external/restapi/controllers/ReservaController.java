package br.com.fiap.squad3.restaurantfinder.external.restapi.controllers;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.usecases.AtualizaStatusReservaUseCase;
import br.com.fiap.squad3.restaurantfinder.application.usecases.CadastroReservaUseCase;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.AtualizaStatusReservaRequestDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaRequestDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaResponsetDto;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.ReservaDtoConverter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    private final CadastroReservaUseCase cadastroReservaUseCase;
    private final AtualizaStatusReservaUseCase atualizaStatusReservaUseCase;
    private final ReservaDtoConverter reservaDtoConverter;

    public ReservaController(
            CadastroReservaUseCase cadastroReservaUseCase,
            AtualizaStatusReservaUseCase atualizaStatusReservaUseCase,
            ReservaDtoConverter reservaDtoConverter
    ) {
        this.cadastroReservaUseCase = cadastroReservaUseCase;
        this.atualizaStatusReservaUseCase = atualizaStatusReservaUseCase;
        this.reservaDtoConverter = reservaDtoConverter;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReservaResponsetDto> save(@Valid @RequestBody ReservaRequestDto reservaRequestDto) {
        Reserva reserva = reservaDtoConverter.toDomain(reservaRequestDto);
        Reserva reservaCadastrada = cadastroReservaUseCase.cadastrar(reserva);
        ReservaResponsetDto reservaCadastradaResponse = reservaDtoConverter.toResponse(reservaCadastrada);

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaCadastradaResponse);
    }

    @PatchMapping("/{idReserva}/status")
    @Transactional
    public ResponseEntity<ReservaResponsetDto> updateStatus(
            @PathVariable Long idReserva,
            @RequestBody AtualizaStatusReservaRequestDto atualizaStatusReservaRequestDto
    ) {
        atualizaStatusReservaUseCase.atualizar(idReserva, atualizaStatusReservaRequestDto.status());

        return ResponseEntity.accepted().build();
    }
}
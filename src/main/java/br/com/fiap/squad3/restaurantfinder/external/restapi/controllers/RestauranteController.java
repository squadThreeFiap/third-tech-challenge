package br.com.fiap.squad3.restaurantfinder.external.restapi.controllers;

import br.com.fiap.squad3.restaurantfinder.application.entities.ReservaDetalhada;
import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.application.usecases.CadastroRestauranteUseCase;
import br.com.fiap.squad3.restaurantfinder.application.usecases.GerenciamentoRestauranteUseCase;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.DataWrapperDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaDetalhadaResponseDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteRequestDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteResponseDto;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.ReservaDtoConverter;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.RestauranteDtoConverter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {
    private final CadastroRestauranteUseCase cadastroRestauranteUseCase;
    private final GerenciamentoRestauranteUseCase gerenciamentoRestauranteUseCase;
    private final RestauranteDtoConverter restauranteDtoConverter;
    private final ReservaDtoConverter reservaDtoConverter;


    public RestauranteController(
            CadastroRestauranteUseCase cadastroRestauranteUseCase,
            GerenciamentoRestauranteUseCase gerenciamentoRestauranteUseCase,
            RestauranteDtoConverter restauranteDtoConverter,
            ReservaDtoConverter reservaDtoConverter
    ) {
        this.cadastroRestauranteUseCase = cadastroRestauranteUseCase;
        this.gerenciamentoRestauranteUseCase = gerenciamentoRestauranteUseCase;
        this.restauranteDtoConverter = restauranteDtoConverter;
        this.reservaDtoConverter = reservaDtoConverter;
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<RestauranteResponseDto> save(@Valid @RequestBody RestauranteRequestDto restauranteRequestDto) {
        Restaurante restaurante = restauranteDtoConverter.toDomain(restauranteRequestDto);
        Restaurante restauranteCadastrado = cadastroRestauranteUseCase.cadastrar(restaurante);
        RestauranteResponseDto restauranteCadastradoResponse = restauranteDtoConverter.toResponse(restauranteCadastrado);

        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteCadastradoResponse);
    }

    @GetMapping("/{idRestaurante}/reservas")
    public ResponseEntity<DataWrapperDto> findReservationsByRestaurantId(@PathVariable Long idRestaurante) {
        List<ReservaDetalhada> reservasDoRestaurante = gerenciamentoRestauranteUseCase.visualizar(idRestaurante);

        List<ReservaDetalhadaResponseDto> reservasDoRestauranteResponse = reservasDoRestaurante.stream()
                .map(reservaDtoConverter::toDetailedResponse)
                .toList();

        return ResponseEntity.ok(new DataWrapperDto(reservasDoRestauranteResponse));
    }

    @GetMapping("/{idRestaurante}/reservas/paginacao")
    public ResponseEntity<DataWrapperDto> findReservationsByRestaurantIdPaginated(
            @PathVariable Long idRestaurante,
            @RequestParam(name = "pagina", defaultValue = "0") int pagina,
            @RequestParam(name = "numeroItensPorPagina", defaultValue = "3") int numeroItensPorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "dataHoraInicio") String ordenarPor,
            @RequestParam(name = "ordemCrescente", defaultValue = "false") boolean ordemCrescente
    ) {
        List<ReservaDetalhada> reservasDoRestaurante = gerenciamentoRestauranteUseCase.visualizar(
                idRestaurante,
                pagina,
                numeroItensPorPagina,
                ordenarPor,
                ordemCrescente
        );

        List<ReservaDetalhadaResponseDto> reservasDoRestauranteResponse = reservasDoRestaurante.stream()
                .map(reservaDtoConverter::toDetailedResponse)
                .toList();

        return ResponseEntity.ok(new DataWrapperDto(reservasDoRestauranteResponse));
    }

//    @PutMapping(
//            value = "/{id}",
//            produces = {"application/json", "application/xml"},
//            consumes = {"application/json", "application/xml"}
//    )
//    @Transactional
//    public ResponseEntity<RestauranteRequest> update(
//            @PathVariable Long id,
//            @Valid @RequestBody RestauranteRequest restauranteRequest
//    ) {
//        return ResponseEntity.ok(restauranteService.update(id, restauranteRequest));
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        restauranteService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<RestauranteRequest>> findAll(
//            @Parameter(hidden = true) @PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable
//    ) {
//        return ResponseEntity.ok(restauranteService.findAll(pageable));
//    }
//
//    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
//    public ResponseEntity<RestauranteRequest> findById(@PathVariable Long id) {
//        return ResponseEntity.ok(restauranteService.findById(id));
//    }
}
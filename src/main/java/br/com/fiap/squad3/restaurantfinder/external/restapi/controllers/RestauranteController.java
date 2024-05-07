package br.com.fiap.squad3.restaurantfinder.external.restapi.controllers;

import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.application.usecases.CadastroRestauranteUseCase;
import br.com.fiap.squad3.restaurantfinder.external.restapi.converter.RestauranteConverter;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteRequest;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {
    private final CadastroRestauranteUseCase cadastroRestauranteUseCase;
    private final RestauranteConverter restauranteConverter;

    public RestauranteController(
            CadastroRestauranteUseCase cadastroRestauranteUseCase,
            RestauranteConverter restauranteConverter
    ) {
        this.cadastroRestauranteUseCase = cadastroRestauranteUseCase;
        this.restauranteConverter = restauranteConverter;
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<RestauranteResponse> save(@Valid @RequestBody RestauranteRequest restauranteRequest) {
        Restaurante restaurante = restauranteConverter.toDomain(restauranteRequest);
        Restaurante restauranteCadastrado = cadastroRestauranteUseCase.cadastrar(restaurante);
        RestauranteResponse restauranteCadastradoResponse = restauranteConverter.toResponse(restauranteCadastrado);

        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteCadastradoResponse);
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
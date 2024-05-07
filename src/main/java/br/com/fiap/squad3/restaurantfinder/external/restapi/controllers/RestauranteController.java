package br.com.fiap.squad3.restaurantfinder.external.restapi.controllers;

import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.services.impl.RestauranteServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {
    private final RestauranteServiceImpl restauranteService;

    public RestauranteController(RestauranteServiceImpl restauranteService) {
        this.restauranteService = restauranteService;
    }

    @PostMapping(
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"}
    )
    @Transactional
    public ResponseEntity<RestauranteDto> save(@Valid @RequestBody RestauranteDto restauranteDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteService.save(restauranteDto));
    }

    @PutMapping(
            value = "/{id}",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"}
    )
    @Transactional
    public ResponseEntity<RestauranteDto> update(
            @PathVariable Long id,
            @Valid @RequestBody RestauranteDto restauranteDto
    ) {
        return ResponseEntity.ok(restauranteService.update(id, restauranteDto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restauranteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<RestauranteDto>> findAll(
            @Parameter(hidden = true) @PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable
    ) {
        return ResponseEntity.ok(restauranteService.findAll(pageable));
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<RestauranteDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(restauranteService.findById(id));
    }
}
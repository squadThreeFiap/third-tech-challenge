package br.com.fiap.squad3.restaurantfinder.external.restapi.controllers;

import br.com.fiap.squad3.restaurantfinder.application.entities.Avaliacao;
import br.com.fiap.squad3.restaurantfinder.application.usecases.AvaliacaoUseCase;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.AvaliacaoRequestDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.AvaliacaoResponseDto;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.AvaliacaoDtoConverter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    private final AvaliacaoUseCase avaliacaoUseCase;
    private final AvaliacaoDtoConverter avaliacaoDtoConverter;

    public AvaliacaoController(AvaliacaoUseCase avaliacaoUseCase, AvaliacaoDtoConverter avaliacaoDtoConverter) {
        this.avaliacaoUseCase = avaliacaoUseCase;
        this.avaliacaoDtoConverter = avaliacaoDtoConverter;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AvaliacaoResponseDto> save(@Valid @RequestBody AvaliacaoRequestDto avaliacaoRequestDto) {
        Avaliacao avaliacao = avaliacaoDtoConverter.toDomain(avaliacaoRequestDto);
        Avaliacao avaliacaoCadastrada = avaliacaoUseCase.cadastrar(avaliacao);
        AvaliacaoResponseDto avaliacaoCadastradaResponse = avaliacaoDtoConverter.toResponse(avaliacaoCadastrada);

        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoCadastradaResponse);
    }

	/*@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<AvaliacaoDto> findById(@PathVariable Long id) {
		return ResponseEntity.ok(avaliacaoService.findById(id));
	}

	@GetMapping(value = "/{cpfCnpj}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Page<AvaliacaoDto>> findByCpfCnpj(
			@Parameter(hidden = true) @PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable) {
		return ResponseEntity.ok(avaliacaoService.findAll(pageable));
	}

	@GetMapping(value = "/{cpfCnpj}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Page<AvaliacaoDto>> findAll(
			@Parameter(hidden = true) @PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable) {
		return ResponseEntity.ok(avaliacaoService.findAll(pageable));
	}*/
}
package external.restapi.controller;

import external.restapi.dtos.AvaliacaoDto;
import external.restapi.service.AvaliacaoService;
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
    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping(
			produces = {"application/json", "application/xml"},
			consumes = {"application/json", "application/xml"}
	)
    @Transactional
    public ResponseEntity<AvaliacaoDto> save(@Valid @RequestBody AvaliacaoDto avaliacaoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.save(avaliacaoDto));
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
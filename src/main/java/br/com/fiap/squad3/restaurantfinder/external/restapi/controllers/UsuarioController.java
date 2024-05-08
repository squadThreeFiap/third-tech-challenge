package br.com.fiap.squad3.restaurantfinder.external.restapi.controllers;

import br.com.fiap.squad3.restaurantfinder.application.entities.Usuario;
import br.com.fiap.squad3.restaurantfinder.application.usecases.CadastroUsuarioUseCase;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.UsuarioRequestDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.UsuarioResponseDto;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.UsuarioDtoConverter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final CadastroUsuarioUseCase cadastroUsuarioUseCase;
    private final UsuarioDtoConverter usuarioDtoConverter;

    public UsuarioController(
            CadastroUsuarioUseCase cadastroUsuarioUseCase,
            UsuarioDtoConverter usuarioDtoConverter
    ) {
        this.cadastroUsuarioUseCase = cadastroUsuarioUseCase;
        this.usuarioDtoConverter = usuarioDtoConverter;
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<UsuarioResponseDto> save(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioDtoConverter.toDomain(usuarioRequestDto);
        Usuario usuarioCadastrado = cadastroUsuarioUseCase.cadastrar(usuario);
        UsuarioResponseDto usuarioCadastradoResponse = usuarioDtoConverter.toResponse(usuarioCadastrado);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCadastradoResponse);
    }

//    @PutMapping(
//            value = "/{id}",
//            produces = {"application/json", "application/xml"},
//            consumes = {"application/json", "application/xml"}
//    )
//    @Transactional
//    public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuarioDto) {
//        return ResponseEntity.ok(usuarioService.update(id, usuarioDto));
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        usuarioService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
//    public ResponseEntity<UsuarioDto> findById(@PathVariable Long id) {
//        return ResponseEntity.ok(usuarioService.findById(id));
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<UsuarioDto>> findAll(
//            @Parameter(hidden = true) @PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable
//    ) {
//        return ResponseEntity.ok(usuarioService.findAll(pageable));
//    }
}
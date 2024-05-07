//package br.com.fiap.squad3.restaurantfinder.external.restapi.controllers;
//
//import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.UsuarioDto;
//import br.com.fiap.squad3.restaurantfinder.external.restapi.services.UsuarioService;
//import io.swagger.v3.oas.annotations.Parameter;
//import jakarta.transaction.Transactional;
//import jakarta.validation.Valid;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/usuario")
//public class UsuarioController {
//    private final UsuarioService usuarioService; // Dependendo da interface
//
//    public UsuarioController(UsuarioService usuarioService) {
//        this.usuarioService = usuarioService;
//    }
//
//    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
//    @Transactional
//    public ResponseEntity<UsuarioDto> save(@Valid @RequestBody UsuarioDto usuarioDto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioDto));
//    }
//
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
//}
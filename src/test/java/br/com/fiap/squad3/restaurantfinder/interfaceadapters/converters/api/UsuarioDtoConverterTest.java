package br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api;

import br.com.fiap.squad3.restaurantfinder.application.entities.Usuario;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.UsuarioRequestDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.UsuarioResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UsuarioDtoConverterTest {

    private UsuarioDtoConverter converter;

    @BeforeEach
    void setUp() {
        converter = new UsuarioDtoConverter();
    }

    @Test
    void testToResponse() {
        Usuario usuario = new Usuario(1L, "12345678900", "Fulano", "11", "987654321", "fulano@example.com", LocalDate.now());

        UsuarioResponseDto responseDto = converter.toResponse(usuario);

        assertEquals(usuario.getId(), responseDto.id());
        assertEquals(usuario.getDataCadastro(), responseDto.dataCadastro());
    }

    @Test
    void testToDomain() {
        UsuarioRequestDto requestDto = new UsuarioRequestDto("33014076090", "Fulano", "11", "987654321", "fulano@example.com", LocalDate.now());

        Usuario usuario = converter.toDomain(requestDto);

        assertNull(usuario.getId());
        assertEquals(requestDto.cpf(), usuario.getCpf());
        assertEquals(requestDto.nome(), usuario.getNome());
        assertEquals(requestDto.ddd(), usuario.getDdd());
        assertEquals(requestDto.telefone(), usuario.getTelefone());
        assertEquals(requestDto.email(), usuario.getEmail());
        assertEquals(requestDto.dataCadastro(), usuario.getDataCadastro());
    }

    @Test
    void testUpdateDomainFromDto() {
        String cpf = "12345678900";
        LocalDate dataCadastro = LocalDate.now();
        Usuario usuario = new Usuario(1L, cpf, "Fulano", "11", "987654321", "fulano@example.com", dataCadastro);
        UsuarioRequestDto requestDto = new UsuarioRequestDto("33014076090", "Fulano C. Beltrano", "13", "123456789", "f.ciclano@example.com", LocalDate.now());

        converter.updateDomainFromDto(usuario, requestDto);

        assertEquals(cpf, usuario.getCpf());
        assertEquals(requestDto.nome(), usuario.getNome());
        assertEquals(requestDto.ddd(), usuario.getDdd());
        assertEquals(requestDto.telefone(), usuario.getTelefone());
        assertEquals(requestDto.email(), usuario.getEmail());
        assertEquals(dataCadastro, usuario.getDataCadastro());
    }
}

package br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db;

import br.com.fiap.squad3.restaurantfinder.application.entities.Usuario;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsuarioEntityConverterTest {

    private UsuarioEntityConverter converter;

    @BeforeEach
    void setUp() {
        converter = new UsuarioEntityConverter();
    }

    @Test
    void testToEntity() {
        Usuario usuario = new Usuario(1L, "12345678900", "Fulano", "11", "987654321", "fulano@example.com", LocalDate.now());

        UsuarioEntity entity = converter.toEntity(usuario);

        assertEquals(usuario.getId(), entity.getId());
        assertEquals(usuario.getCpf(), entity.getCpf());
        assertEquals(usuario.getNome(), entity.getNome());
        assertEquals(usuario.getDdd(), entity.getDdd());
        assertEquals(usuario.getTelefone(), entity.getTelefone());
        assertEquals(usuario.getEmail(), entity.getEmail());
        assertEquals(usuario.getDataCadastro(), entity.getDataCadastro());
    }

    @Test
    void testToDomainObj() {
        UsuarioEntity entity = new UsuarioEntity(1L, "33014076090", "F. C. Beltrano", "11", "987654321", "fc_beltrano@example.com", LocalDate.now());

        Usuario usuario = converter.toDomainObj(entity);

        assertEquals(entity.getId(), usuario.getId());
        assertEquals(entity.getCpf(), usuario.getCpf());
        assertEquals(entity.getNome(), usuario.getNome());
        assertEquals(entity.getDdd(), usuario.getDdd());
        assertEquals(entity.getTelefone(), usuario.getTelefone());
        assertEquals(entity.getEmail(), usuario.getEmail());
        assertEquals(entity.getDataCadastro(), usuario.getDataCadastro());
    }
}

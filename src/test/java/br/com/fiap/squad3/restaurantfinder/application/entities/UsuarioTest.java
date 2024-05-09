package br.com.fiap.squad3.restaurantfinder.application.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsuarioTest {

    @Test
    void testGettersAndSetters() {
        Usuario usuario = new Usuario(1L, "12345678900", "Fulano", "11", "987654321", "fulano@example.com", LocalDate.now());

        assertEquals(1L, usuario.getId());
        assertEquals("12345678900", usuario.getCpf());
        assertEquals("Fulano", usuario.getNome());
        assertEquals("11", usuario.getDdd());
        assertEquals("987654321", usuario.getTelefone());
        assertEquals("fulano@example.com", usuario.getEmail());
        assertEquals(LocalDate.now(), usuario.getDataCadastro());
    }

    @Test
    void testSetCpfNull() {
        Usuario usuario = new Usuario(1L, null, "Fulano", "11", "987654321", "fulano@example.com", LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> usuario.setCpf(null));
    }

    @Test
    void testSetNomeNull() {
        Usuario usuario = new Usuario(1L, "12345678900", null, "11", "987654321", "fulano@example.com", LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> usuario.setNome(null));
    }

    @Test
    void testSetDddNull() {
        Usuario usuario = new Usuario(1L, "12345678900", "Fulano", null, "987654321", "fulano@example.com", LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> usuario.setDdd(null));
    }

    @Test
    void testSetTelefoneNull() {
        Usuario usuario = new Usuario(1L, "12345678900", "Fulano", "11", null, "fulano@example.com", LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> usuario.setTelefone(null));
    }

    @Test
    void testSetEmailNull() {
        Usuario usuario = new Usuario(1L, "12345678900", "Fulano", "11", "987654321", null, LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> usuario.setEmail(null));
    }
}

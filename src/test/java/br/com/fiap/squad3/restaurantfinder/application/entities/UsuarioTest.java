package br.com.fiap.squad3.restaurantfinder.application.entities;

import mocks.UsuarioMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new UsuarioMock();
    }

    @Test
    void testGettersAndSetters() {
        Usuario usuario = new Usuario(id, cpf, nome, ddd, telefone, email, dataCadastro);

        assertEquals(id, usuario.getId());
        assertEquals(cpf, usuario.getCpf());
        assertEquals(nome, usuario.getNome());
        assertEquals(ddd, usuario.getDdd());
        assertEquals(telefone, usuario.getTelefone());
        assertEquals(email, usuario.getEmail());
        assertEquals(dataCadastro, usuario.getDataCadastro());
    }

    @Test
    void testSetCpfNull() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setCpf(null));
        assertThrows(IllegalArgumentException.class, () -> new Usuario(1L, null, "Fulano", "11", "987654321", "fulano@example.com", LocalDate.now()));
    }

    @Test
    void testSetNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setNome(null));
        assertThrows(IllegalArgumentException.class, () -> new Usuario(1L, "12345678900", null, "11", "987654321", "fulano@example.com", LocalDate.now()));
    }

    @Test
    void testSetDddNull() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setDdd(null));
        assertThrows(IllegalArgumentException.class, () -> new Usuario(1L, "12345678900", "Fulano", null, "987654321", "fulano@example.com", LocalDate.now()));
    }

    @Test
    void testSetTelefoneNull() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setTelefone(null));
        assertThrows(IllegalArgumentException.class, () -> new Usuario(1L, "12345678900", "Fulano", "11", null, "fulano@example.com", LocalDate.now()));
    }

    @Test
    void testSetEmailNull() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setEmail(null));
        assertThrows(IllegalArgumentException.class, () -> new Usuario(1L, "12345678900", "Fulano", "11", "987654321", null, LocalDate.now()));
    }
}

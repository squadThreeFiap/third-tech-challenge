package br.com.fiap.squad3.restaurantfinder.application.entities;

import mocks.UsuarioMock;
import org.junit.jupiter.api.Test;
import utils.TextoUtil;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    private Long id = 1L;
    private String cpf = "12345678900";
    private String nome = "Fulano";
    private String ddd = "11";
    private String telefone = "987654321";
    private String email = "fulano@example.com";
    private LocalDate dataCadastro = LocalDate.of(2024, 5, 13);

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
    void testGettersAndSettersSemId() {
        Usuario usuario = new Usuario(cpf, nome, ddd, telefone, email, dataCadastro);

        assertNull(usuario.getId());
        assertEquals(cpf, usuario.getCpf());
        assertEquals(nome, usuario.getNome());
        assertEquals(ddd, usuario.getDdd());
        assertEquals(telefone, usuario.getTelefone());
        assertEquals(email, usuario.getEmail());
        assertEquals(dataCadastro, usuario.getDataCadastro());
    }

    @Test
    void testSettersSemConstrutor() {
        Usuario usuario = new UsuarioMock();

        usuario.setId(id);
        usuario.setDataCadastro(dataCadastro);

        assertEquals(id, usuario.getId());
        assertEquals(dataCadastro, usuario.getDataCadastro());
    }

    @Test
    void testSetCpfNull() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario(null, nome, ddd, telefone, email, dataCadastro));
        assertThrows(IllegalArgumentException.class, () -> new Usuario(id, null, nome, ddd, telefone, email, dataCadastro));
    }

    @Test
    void testSetNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario(cpf, null, ddd, telefone, email, dataCadastro));
        assertThrows(IllegalArgumentException.class, () -> new Usuario(id, cpf, null, ddd, telefone, email, dataCadastro));
    }

    @Test
    void testSetNomeQueExcedeMaximoPermitido() {
        Usuario usuario = new UsuarioMock();

        String nomeExtenso = TextoUtil.criaTextoRandomico(usuario.TAMANHO_MAXIMO_NOME + 1);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> usuario.setNome(nomeExtenso));
        assertEquals("O campo 'nome' deve ter no máximo " +
                usuario.TAMANHO_MAXIMO_NOME + " caracteres.", ex.getMessage());
    }

    @Test
    void testSetDddNull() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario(cpf, nome, null, telefone, email, dataCadastro));
        assertThrows(IllegalArgumentException.class, () -> new Usuario(id, cpf, nome, null, telefone, email, dataCadastro));
    }

    @Test
    void testSetTelefoneNull() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario(cpf, nome, ddd, null, email, dataCadastro));
        assertThrows(IllegalArgumentException.class, () -> new Usuario(id, cpf, nome, ddd, null, email, dataCadastro));
    }

    @Test
    void testSetEmailNull() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario(cpf, nome, ddd, telefone, null, dataCadastro));
        assertThrows(IllegalArgumentException.class, () -> new Usuario(id, cpf, nome, ddd, telefone, null, dataCadastro));
    }
}

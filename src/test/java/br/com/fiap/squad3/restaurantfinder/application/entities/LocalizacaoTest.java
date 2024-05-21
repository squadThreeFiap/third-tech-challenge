package br.com.fiap.squad3.restaurantfinder.application.entities;

import br.com.fiap.squad3.restaurantfinder.application.entities.Localizacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocalizacaoTest {

    private Localizacao localizacao;

    @BeforeEach
    void setUp() {
        localizacao = new Localizacao("12345678", "SP", "São Paulo", "Centro", "Rua Principal", 123);
    }

    @Test
    void testGetters() {
        String cep = "12345678";
        String uf = "SP";
        String cidade = "São Paulo";
        String bairro = "Centro";
        String logradouro = "Rua Principal";
        Integer numero = 123;

        Localizacao localizacao = new Localizacao(cep, uf, cidade, bairro, logradouro, numero);

        assertEquals(cep, localizacao.getCep());
        assertEquals(uf, localizacao.getUf());
        assertEquals(cidade, localizacao.getCidade());
        assertEquals(bairro, localizacao.getBairro());
        assertEquals(logradouro, localizacao.getLogradouro());
        assertEquals(numero, localizacao.getNumero());
    }

    @Test
    void testValidacaoCep() {
        assertThrows(IllegalArgumentException.class, () -> localizacao.setCep("10009-100"));
        assertThrows(IllegalArgumentException.class, () -> localizacao.setCep("123"));
    }

    @Test
    void testValidacaoUf() {
        assertThrows(IllegalArgumentException.class, () -> localizacao.setUf(null));
        assertThrows(IllegalArgumentException.class, () -> localizacao.setUf(" "));
        assertThrows(IllegalArgumentException.class, () -> localizacao.setUf("ABC"));
        assertThrows(IllegalArgumentException.class, () -> new Localizacao("12345678", null, "Cidade", "Bairro", "Logradouro", 1));
        assertThrows(IllegalArgumentException.class, () -> new Localizacao("12345678", "", "Cidade", "Bairro", "Logradouro", 1));
        assertThrows(IllegalArgumentException.class, () -> new Localizacao("12345678", "ABC", "Cidade", "Bairro", "Logradouro", 1));
    }

    @Test
    void testValidacaoCidadeViaConstrutor() {
        assertThrows(IllegalArgumentException.class, () -> localizacao.setCidade(null));
        assertThrows(IllegalArgumentException.class, () -> localizacao.setCidade(""));
    }

    @Test
    void testValidacaoBairro() {
        assertThrows(IllegalArgumentException.class, () -> localizacao.setBairro(null));
        assertThrows(IllegalArgumentException.class, () -> localizacao.setBairro(""));
    }

    @Test
    void testValidacaoLogradouro() {
        assertThrows(IllegalArgumentException.class, () -> localizacao.setLogradouro(null));
        assertThrows(IllegalArgumentException.class, () -> localizacao.setLogradouro(""));
    }

    @Test
    void testGetAndSetNumero() {
        Integer numero = 123;

        localizacao.setNumero(numero);

        assertEquals(numero, localizacao.getNumero());
    }
}
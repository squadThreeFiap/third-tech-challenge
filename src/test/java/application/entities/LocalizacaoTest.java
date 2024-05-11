package application.entities;

import br.com.fiap.squad3.restaurantfinder.application.entities.Localizacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocalizacaoTest {

    private Localizacao localizacao;

    @BeforeEach
    void setUp() {
        localizacao = new Localizacao("12345678","SP", "São Paulo", "Centro", "Rua Principal", 123);
    }

    @Test
    void testValidacaoUf() {
        assertThrows(IllegalArgumentException.class, () -> localizacao.setUf(null));
        assertThrows(IllegalArgumentException.class, () -> localizacao.setUf(" "));
        assertThrows(IllegalArgumentException.class, () -> localizacao.setUf("ABC"));
        assertThrows(IllegalArgumentException.class, () -> new Localizacao("12345678",null, "Cidade", "Bairro", "Logradouro", 1));
        assertThrows(IllegalArgumentException.class, () -> new Localizacao("12345678","", "Cidade", "Bairro", "Logradouro", 1));
        assertThrows(IllegalArgumentException.class, () -> new Localizacao("12345678","ABC", "Cidade", "Bairro", "Logradouro", 1));
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
    void testGetNumero() {
        assertEquals(123, localizacao.getNumero());
    }
}
package application.entities;

import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {
    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante(1L, "Restaurante Teste", "Italiana", 10);
    }

    @Test
    void setNomeComSucesso() {
        String novoNome = "Restaurante Novo Nome";
        restaurante.setNome(novoNome);
        assertEquals(novoNome, restaurante.getNome());
    }

    @Test
    void setNomeComFalha() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            restaurante.setNome("");
        });
        assertEquals("Nome deve ser informado.", exception.getMessage());
    }

    @Test
    void setTipoCozinhaComSucesso() {
        String novoTipoCozinha = "Brasileira";
        restaurante.setTipoCozinha(novoTipoCozinha);
        assertEquals(novoTipoCozinha, restaurante.getTipoCozinha());
    }

    @Test
    void setTipoCozinhaComFalha() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            restaurante.setTipoCozinha("");
        });
        assertEquals("Tipo de cozinha deve ser informada.", exception.getMessage());
    }

    @Test
    void setCapacidadeComSucesso() {
        Integer novaCapacidade = 20;
        restaurante.setCapacidade(novaCapacidade);
        assertEquals(novaCapacidade, restaurante.getCapacidade());
    }

    @Test
    void setCapacidadeComFalha() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            restaurante.setCapacidade(0);
        });
        assertTrue(exception.getMessage().contains("Capacidade mínima é 1."));
    }
}
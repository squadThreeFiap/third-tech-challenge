package application.entities;

import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {
    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante(1L, "Restaurante", "Tipo Cozinha", 100);
    }

    @Test
    public void testValidacaoNome() {
        assertThrows(IllegalArgumentException.class, () -> restaurante.setNome(null));
        assertThrows(IllegalArgumentException.class, () -> restaurante.setNome(""));
        assertEquals("Restaurante", restaurante.getNome());
    }

    @Test
    public void testValidacaoTipoCozinha() {
        assertThrows(IllegalArgumentException.class, () -> restaurante.setNome(null));
        assertThrows(IllegalArgumentException.class, () -> restaurante.setNome(""));
        assertEquals("Tipo Cozinha", restaurante.getTipoCozinha());
    }

    @Test
    public void testValidacaoCapacidade() {
        assertThrows(IllegalArgumentException.class, () -> restaurante.setCapacidade(0));
        assertEquals(100, restaurante.getCapacidade());
    }
}
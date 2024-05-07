package application.entities;

import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {
    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante("Restaurante", "Tipo Cozinha", 100);
    }

    @Test
    public void testValidacaoNome() {
        fail("Teste pendente de implementação.");
    }

    @Test
    public void testValidacaoTipoCozinha() {
        fail("Teste pendente de implementação.");
    }

    @Test
    public void testValidacaoCapacidade() {
        fail("Teste pendente de implementação.");
    }
}
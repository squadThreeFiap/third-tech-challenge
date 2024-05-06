package application.entities;

import application.entities.enums.DiaSemana;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

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
package application.entities;

import br.com.fiap.squad3.restaurantfinder.application.entities.Funcionamento;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.DiaSemana;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FuncionamentoTest {
    private Funcionamento funcionamento;

    @BeforeEach
    void setUp() {
        Set<DiaSemana> dias = new HashSet<>();
        dias.add(DiaSemana.SEGUNDA);
        dias.add(DiaSemana.QUARTA);
        dias.add(DiaSemana.SEXTA);

        LocalTime horaAbertura = LocalTime.of(9, 0);
        LocalTime horaEncerramento = LocalTime.of(18, 0);

        funcionamento = new Funcionamento(dias, horaAbertura, horaEncerramento);
    }

    @Test
    void testDiasDeFuncionamentoNaoPodemSerVazios() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Funcionamento(new HashSet<>(), LocalTime.now(), LocalTime.now())
        );
    }

    @Test
    void testGetHoraAbertura() {
        assertEquals(LocalTime.of(9, 0), funcionamento.getHoraAbertura());
    }

    @Test
    void testGetHoraEncerramento() {
        assertEquals(LocalTime.of(18, 0), funcionamento.getHoraEncerramento());
    }
}
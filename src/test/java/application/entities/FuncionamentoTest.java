package application.entities;

import br.com.fiap.squad3.restaurantfinder.application.entities.Funcionamento;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.DiaSemana;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FuncionamentoTest {
    private Set<DiaSemana> dias = new HashSet(List.of(DiaSemana.SEGUNDA, DiaSemana.QUARTA, DiaSemana.SEXTA));
    private LocalTime horaAbertura = LocalTime.of(9, 0);
    private LocalTime horaEncerramento = LocalTime.of(18, 0);

    @Test
    void testGettersAndSetters() {
        Funcionamento funcionamento = new Funcionamento(dias, horaAbertura, horaEncerramento);

        assertEquals(dias, funcionamento.getDias());
        assertEquals(horaAbertura, funcionamento.getHoraAbertura());
        assertEquals(horaEncerramento, funcionamento.getHoraEncerramento());
    }

    @Test
    void testDiasDeFuncionamentoNaoPodemSerVazios() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Funcionamento(new HashSet<>(), horaAbertura, horaEncerramento)
        );
    }
}
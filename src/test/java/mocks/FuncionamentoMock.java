package mocks;

import br.com.fiap.squad3.restaurantfinder.application.entities.Funcionamento;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.DiaSemana;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

public class FuncionamentoMock extends Funcionamento {
    public FuncionamentoMock() {
        super(
                new HashSet(List.of(DiaSemana.SEGUNDA, DiaSemana.QUARTA, DiaSemana.SEXTA)),
                LocalTime.of(9, 0),
                LocalTime.of(18, 0)
        );
    }
}

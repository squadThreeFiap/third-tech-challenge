package application.entities;

import application.entities.enums.DiaSemana;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

public class Funcionamento {
    private Set<DiaSemana> dias;
    private final LocalTime horaAbertura;
    private final LocalTime horaEncerramento;

    public Funcionamento(Set<DiaSemana> dias, LocalTime horaAbertura, LocalTime horaEncerramento) {
        this.setDias(dias);

        this.horaAbertura = horaAbertura;
        this.horaEncerramento = horaEncerramento;
    }

    public Set<DiaSemana> getDias() {
        return Collections.unmodifiableSet(dias);
    }

    public void setDias(Set<DiaSemana> dias) {
        if (dias.isEmpty()) {
            throw new IllegalArgumentException("Restaurantes devem ter pelo menos um dia de funcionamento.");
        }

        this.dias = dias;
    }

    public LocalTime getHoraAbertura() {
        return horaAbertura;
    }

    public LocalTime getHoraEncerramento() {
        return horaEncerramento;
    }
}

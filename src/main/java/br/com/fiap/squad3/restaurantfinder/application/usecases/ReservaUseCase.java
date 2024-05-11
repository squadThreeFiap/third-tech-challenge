package br.com.fiap.squad3.restaurantfinder.application.usecases;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.StatusReserva;
import br.com.fiap.squad3.restaurantfinder.application.gateways.ReservaGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.RestauranteGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.UsuarioGateway;

import java.time.Duration;
import java.time.LocalDateTime;

public class ReservaUseCase {
    private static final int PERMANCENCIA_MINIMA = 1;
    private static final int PERMANCENCIA_MAXIMA = 5;

    private final UsuarioGateway usuarioGateway;
    private final RestauranteGateway restauranteGateway;
    private final ReservaGateway reservaGateway;

    public ReservaUseCase(
            UsuarioGateway usuarioGateway,
            RestauranteGateway restauranteGateway,
            ReservaGateway reservaGateway
    ) {
        this.usuarioGateway = usuarioGateway;
        this.restauranteGateway = restauranteGateway;
        this.reservaGateway = reservaGateway;
    }

    public Reserva cadastrar(Reserva reserva) {
        return this.cadastrar(
                reserva.getIdUsuario(),
                reserva.getIdRestaurante(),
                reserva.getQuantidadePessoas(),
                reserva.getDataHoraInicio(),
                reserva.getDataHoraFim()
        );
    }

    public Reserva cadastrar(
            Long idUsuario,
            Long idRestaurante,
            Integer quantidadePessoas,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim
    ) {
        validarConsistenciaDoHorarioDoAgendamento(dataHoraInicio, dataHoraFim);
        validarIntegridadeDosDadosNaBaseDeDados(idUsuario, idRestaurante);
        validarDisponibilidadeNoHorarioAgendado(idRestaurante, dataHoraInicio, dataHoraFim, quantidadePessoas);

        Reserva reserva = new Reserva(
                idUsuario,
                idRestaurante,
                quantidadePessoas,
                dataHoraInicio,
                dataHoraFim,
                StatusReserva.AGENDADO
        );

        return reservaGateway.cadastrar(reserva);
    }

    private void validarConsistenciaDoHorarioDoAgendamento(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        if (!verificaDiaReserva(dataHoraInicio, dataHoraFim)) {
            throw new IllegalArgumentException("Data de agendamento inicial deve acontecer no mesmo dia que o fim.");
        }

        if (!verificaHoraInicialAposFinal(dataHoraInicio, dataHoraFim)) {
            throw new IllegalArgumentException("Horário inicial do agendamento deve preceder o final.");
        }

        if (!verificaPermanenciaMinima(dataHoraInicio, dataHoraFim)) {
            throw new IllegalArgumentException("Permanência mínima é de " + PERMANCENCIA_MINIMA + "h.");
        }

        if (!verificaPermanenciaMaxima(dataHoraInicio, dataHoraFim)) {
            throw new IllegalArgumentException("Permanência máxima é de " + PERMANCENCIA_MAXIMA + "h.");
        }
    }

    private boolean verificaDiaReserva(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        return dataHoraInicio.getDayOfMonth() == dataHoraFim.getDayOfMonth();
    }

    private boolean verificaHoraInicialAposFinal(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        return dataHoraInicio.getHour() > dataHoraFim.getHour();
    }

    private boolean verificaPermanenciaMinima(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        return calcularPermanencia(dataHoraInicio, dataHoraFim) < PERMANCENCIA_MINIMA;
    }

    private boolean verificaPermanenciaMaxima(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        return calcularPermanencia(dataHoraInicio, dataHoraFim) > PERMANCENCIA_MAXIMA;
    }

    private Integer calcularPermanencia(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        return Duration.between(dataHoraInicio, dataHoraFim).toHoursPart();
    }

    private void validarIntegridadeDosDadosNaBaseDeDados(Long idUsuario, Long idRestaurante) {
        if (!usuarioGateway.verificarSeExistePeloId(idUsuario)) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        if (!restauranteGateway.verificarSeExiste(idRestaurante)) {
            throw new IllegalArgumentException("Restaurante não encontrado.");
        }
    }

    private void validarDisponibilidadeNoHorarioAgendado(
            Long idRestaurante,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim,
            Integer quantidadePessoas
    ) {
        Boolean estaDisponivelParaReservar = reservaGateway.verificarSeEstaDisponivelParaReservar(
                idRestaurante,
                dataHoraInicio,
                dataHoraFim,
                quantidadePessoas
        );

        if (!estaDisponivelParaReservar) {
            throw new IllegalArgumentException("Restaurante não suporta mais reservas no horário selecionado.");
        }
    }
}

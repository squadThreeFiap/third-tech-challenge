package br.com.fiap.squad3.restaurantfinder.application.usecases;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.StatusReserva;
import br.com.fiap.squad3.restaurantfinder.application.gateways.ReservaGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.RestauranteGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.UsuarioGateway;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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
        Reserva reserva = new Reserva(
                idUsuario,
                idRestaurante,
                quantidadePessoas,
                dataHoraInicio,
                dataHoraFim
        );

        validarConsistenciaDoHorarioDoAgendamento(dataHoraInicio, dataHoraFim);
        validarIntegridadeDosDadosNaBaseDeDados(idUsuario, idRestaurante);
        validarDisponibilidadeNoHorarioAgendado(idRestaurante, dataHoraInicio, dataHoraFim, quantidadePessoas);

        reserva.setStatus(StatusReserva.AGENDADO);

        return reservaGateway.cadastrar(reserva);
    }

    private void validarConsistenciaDoHorarioDoAgendamento(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        if(!verificaSeDataHoraEstaNoFuturo(dataHoraInicio)){
            throw new IllegalArgumentException("Data de início da reserva não pode estar no passado.");
        }

        if(!verificaSeDataHoraEstaNoFuturo(dataHoraFim)){
            throw new IllegalArgumentException("Data de fim da reserva não pode estar no passado.");
        }

        if (!verificaSeAgendamentoEstaNoMesmoDia(dataHoraInicio, dataHoraFim)) {
            throw new IllegalArgumentException("Data de agendamento inicial deve acontecer no mesmo dia que o fim.");
        }

        if (!verificaSeHoraInicialAntecedeFinal(dataHoraInicio, dataHoraFim)) {
            throw new IllegalArgumentException("Horário inicial do agendamento deve preceder o final.");
        }

        if (!verificaSePermanenciaMinimaEstaValida(dataHoraInicio, dataHoraFim)) {
            throw new IllegalArgumentException("Permanência mínima é de " + PERMANCENCIA_MINIMA + "h.");
        }

        if (!verificaSePermanenciaMaximaEstaValida(dataHoraInicio, dataHoraFim)) {
            throw new IllegalArgumentException("Permanência máxima é de " + PERMANCENCIA_MAXIMA + "h.");
        }
    }

    private boolean verificaSeDataHoraEstaNoFuturo(LocalDateTime dataHora) {
        return dataHora.isAfter(LocalDateTime.now());
    }

    private boolean verificaSeAgendamentoEstaNoMesmoDia(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        return dataHoraInicio.getDayOfMonth() == dataHoraFim.getDayOfMonth();
    }

    private boolean verificaSeHoraInicialAntecedeFinal(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        return dataHoraInicio.getHour() < dataHoraFim.getHour();
    }

    private boolean verificaSePermanenciaMinimaEstaValida(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        return calcularPermanencia(dataHoraInicio, dataHoraFim) >= PERMANCENCIA_MINIMA;
    }

    private boolean verificaSePermanenciaMaximaEstaValida(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        return calcularPermanencia(dataHoraInicio, dataHoraFim) <= PERMANCENCIA_MAXIMA;
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
        Boolean estaDisponivelParaReservar = verificarSeEstaDisponivelParaReservar(
                idRestaurante,
                dataHoraInicio,
                dataHoraFim,
                quantidadePessoas
        );

        if (!estaDisponivelParaReservar) {
            throw new IllegalArgumentException("Restaurante não suporta mais reservas no horário selecionado.");
        }
    }

    private boolean verificarSeEstaDisponivelParaReservar(
            Long idRestaurante,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim,
            Integer quantidadePessoas
    ) {
        List<Reserva> reservasEmAndamento = reservaGateway.obterTodasAsReservasOcupadasNoHorarioAgendado(
                idRestaurante,
                dataHoraInicio,
                dataHoraFim
        );

        Integer capacidadeOcupada = reservasEmAndamento.stream()
                .mapToInt(Reserva::getQuantidadePessoas)
                .sum();

        Restaurante restaurante = restauranteGateway.buscarPeloId(idRestaurante);
        Integer capacidadeSuportadoNoRestaurante = restaurante.getCapacidade();

        return (capacidadeOcupada + quantidadePessoas) <= capacidadeSuportadoNoRestaurante;
    }
}

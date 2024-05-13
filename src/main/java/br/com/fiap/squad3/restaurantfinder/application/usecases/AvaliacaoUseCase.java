package br.com.fiap.squad3.restaurantfinder.application.usecases;

import br.com.fiap.squad3.restaurantfinder.application.entities.Avaliacao;
import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.StatusReserva;
import br.com.fiap.squad3.restaurantfinder.application.gateways.AvaliacaoGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.ReservaGateway;

import java.util.List;

public class AvaliacaoUseCase {
    private final ReservaGateway reservaGateway;
    private final AvaliacaoGateway avaliacaoGateway;

    public AvaliacaoUseCase(ReservaGateway reservaGateway, AvaliacaoGateway avaliacaoGateway) {
        this.reservaGateway = reservaGateway;
        this.avaliacaoGateway = avaliacaoGateway;
    }

    public Avaliacao cadastrar(Avaliacao avaliacao) {
        validarReservaDaAvaliacao(avaliacao);

        return this.avaliacaoGateway.cadastrar(avaliacao);
    }

    private void validarReservaDaAvaliacao(Avaliacao avaliacao) {
        Reserva reservaParaAvaliar = reservaGateway.buscarPeloId(avaliacao.getIdReserva());

        validarReserva(reservaParaAvaliar);
        validarStatusDaReserva(reservaParaAvaliar);
        validarSeReservaJaFoiAvaliada(reservaParaAvaliar);
    }

    private void validarReserva(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("Avalição não pode ser realizada pois a reserva não foi encontrada.");
        }
    }

    private void validarStatusDaReserva(Reserva reserva) {
        List<StatusReserva> statusAvaliaveis = List.of(StatusReserva.CONCLUIDA, StatusReserva.CANCELADA);

        if (!statusAvaliaveis.contains(reserva.getStatus())) {
            throw new IllegalArgumentException("Reserva não se encontra em um status disponível para avaliação. " +
                    "Para ralizar a avaliação a reserva deve estar em um desses status: " + statusAvaliaveis + ".");
        }
    }

    private void validarSeReservaJaFoiAvaliada(Reserva reserva) {
        Avaliacao avaliacao = avaliacaoGateway.buscarPeloIdDaReserva(reserva.getId());

        if(avaliacao != null) {
            throw new IllegalArgumentException("A reserva já foi avaliada.");
        }
    }
}
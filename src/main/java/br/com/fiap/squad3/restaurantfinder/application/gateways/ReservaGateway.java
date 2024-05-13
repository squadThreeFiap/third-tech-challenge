package br.com.fiap.squad3.restaurantfinder.application.gateways;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.entities.ReservaDetalhada;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaGateway {
    List<Reserva> obterTodasAsReservasOcupadasNoHorarioAgendado(
            Long idRestaurante,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim
    );

    Reserva cadastrar(Reserva reserva);

    List<Reserva> obterReservasDoUsuarioNoHorarioAgendado(
            Long idUsuario,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim
    );

    Reserva buscarPeloId(Long id);

    List<ReservaDetalhada> buscarDetalhesPeloIdDoRestaurante(Long idRestaurante);

    List<ReservaDetalhada> buscarDetalhesPeloIdDoRestaurante(
            Long idRestaurante,
            int pagina,
            int numeroItensPorPagina,
            String ordenarPor,
            boolean ordemCrescente
    );
}

package br.com.fiap.squad3.restaurantfinder.application.usecases;

import br.com.fiap.squad3.restaurantfinder.application.entities.Avaliacao;
import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.StatusReserva;
import br.com.fiap.squad3.restaurantfinder.application.gateways.AvaliacaoGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.ReservaGateway;
import mocks.AvaliacaoMock;
import mocks.ReservaMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AvaliacaoUseCaseTest {
    private AvaliacaoUseCase avaliacaoUseCase;

    @Mock
    private ReservaGateway reservaGateway;

    @Mock
    private AvaliacaoGateway avaliacaoGateway;

    private Reserva reservaCadastradaNoSistema;

    @BeforeEach
    public void setUp() {
        reservaGateway = mock(ReservaGateway.class);
        avaliacaoGateway = mock(AvaliacaoGateway.class);

        avaliacaoUseCase = new AvaliacaoUseCase(reservaGateway, avaliacaoGateway);

        reservaCadastradaNoSistema = new ReservaMock();
        reservaCadastradaNoSistema.setId(10L);
    }

    @Test
    public void testCadastrar_ReservaValida() {
        reservaCadastradaNoSistema.setStatus(StatusReserva.CONCLUIDA);

        Avaliacao avaliacao = new AvaliacaoMock();
        avaliacao.setIdReserva(reservaCadastradaNoSistema.getId());

        when(reservaGateway.buscarPeloId(avaliacao.getIdReserva())).thenReturn(reservaCadastradaNoSistema);

        Avaliacao avaliacaoCadastrada = new AvaliacaoMock();
        avaliacaoCadastrada.setId(100L);

        when(avaliacaoGateway.cadastrar(avaliacao)).thenReturn(avaliacaoCadastrada);

        Avaliacao resultado = avaliacaoUseCase.cadastrar(avaliacao);

        assertEquals(avaliacaoCadastrada, resultado);
        verify(reservaGateway, times(1)).buscarPeloId(reservaCadastradaNoSistema.getId());
        verify(avaliacaoGateway, times(1)).cadastrar(avaliacao);
    }

    @Test
    public void testCadastrar_ValoresInvalidos_ReservaInexistente() {
        Avaliacao avaliacao = new AvaliacaoMock();

        when(reservaGateway.buscarPeloId(avaliacao.getIdReserva())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> avaliacaoUseCase.cadastrar(avaliacao));
        verify(reservaGateway, times(1)).buscarPeloId(avaliacao.getIdReserva());
        verify(avaliacaoGateway, never()).cadastrar(any());
    }

    @Test
    public void testValidarStatusDaReserva_StatusInvalido_Agendado() {
        reservaCadastradaNoSistema.setStatus(StatusReserva.AGENDADO);

        Avaliacao avaliacao = new AvaliacaoMock();
        avaliacao.setIdReserva(reservaCadastradaNoSistema.getId());

        when(reservaGateway.buscarPeloId(reservaCadastradaNoSistema.getId())).thenReturn(reservaCadastradaNoSistema);

        assertThrows(IllegalArgumentException.class, () -> avaliacaoUseCase.cadastrar(avaliacao));
        verify(reservaGateway, times(1)).buscarPeloId(avaliacao.getIdReserva());
        verify(avaliacaoGateway, never()).cadastrar(any());
    }

    @Test
    public void testValidarStatusDaReserva_StatusInvalido_Andamento() {
        reservaCadastradaNoSistema.setStatus(StatusReserva.ANDAMENTO);

        Avaliacao avaliacao = new AvaliacaoMock();
        avaliacao.setIdReserva(reservaCadastradaNoSistema.getId());

        when(reservaGateway.buscarPeloId(reservaCadastradaNoSistema.getId())).thenReturn(reservaCadastradaNoSistema);

        assertThrows(IllegalArgumentException.class, () -> avaliacaoUseCase.cadastrar(avaliacao));
        verify(reservaGateway, times(1)).buscarPeloId(avaliacao.getIdReserva());
        verify(avaliacaoGateway, never()).cadastrar(any());
    }

    @Test
    public void testCadastrar_ValoresInvalidos_ReservaJaFoiAvaliada() {
        reservaCadastradaNoSistema.setStatus(StatusReserva.CONCLUIDA);

        Avaliacao avaliacao = new AvaliacaoMock();
        avaliacao.setIdReserva(reservaCadastradaNoSistema.getId());

        when(reservaGateway.buscarPeloId(avaliacao.getIdReserva())).thenReturn(reservaCadastradaNoSistema);

        when(avaliacaoGateway.buscarPeloIdDaReserva(avaliacao.getIdReserva())).thenReturn(avaliacao);

        assertThrows(IllegalArgumentException.class, () -> avaliacaoUseCase.cadastrar(avaliacao));
        verify(reservaGateway, times(1)).buscarPeloId(avaliacao.getIdReserva());
        verify(avaliacaoGateway, times(1)).buscarPeloIdDaReserva(avaliacao.getIdReserva());
        verify(avaliacaoGateway, never()).cadastrar(any());
    }
}
package br.com.fiap.squad3.restaurantfinder.application.usecases;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.StatusReserva;
import br.com.fiap.squad3.restaurantfinder.application.gateways.ReservaGateway;
import mocks.ReservaMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AtualizaStatusReservaUseCaseTest {
    @Mock
    private ReservaGateway reservaGateway;

    @InjectMocks
    private AtualizaStatusReservaUseCase atualizaStatusReservaUseCase;

    @BeforeEach
    void setUp() {
        reservaGateway = mock(ReservaGateway.class);

        atualizaStatusReservaUseCase = new AtualizaStatusReservaUseCase(reservaGateway);
    }

    @Test
    void testAtualizar_Sucesso() {
        Reserva reservaEsperada = new ReservaMock();
        StatusReserva novoStatusEsperado = StatusReserva.CANCELADA;

        when(reservaGateway.verificarSeExiste(reservaEsperada.getId())).thenReturn(true);

        when(reservaGateway.alterarStatus(reservaEsperada.getId(), novoStatusEsperado))
                .thenReturn(new ReservaMock(novoStatusEsperado));

        Reserva result = atualizaStatusReservaUseCase.atualizar(
                reservaEsperada.getId(),
                novoStatusEsperado.toString()
        );

        assertEquals(reservaEsperada.getId(), result.getId());
        assertEquals(novoStatusEsperado, result.getStatus());
        verify(reservaGateway, times(1)).verificarSeExiste(reservaEsperada.getId());
        verify(reservaGateway, times(1)).alterarStatus(reservaEsperada.getId(), novoStatusEsperado);
    }

    @Test
    void testAtualizar_ValorInvalido_StatusDesconhecido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> atualizaStatusReservaUseCase.atualizar(
                        10L,
                        "PENDENTE_DE_CONFIRMACAO_POR_MULTIPLO_FATOR_DE_AUTENTICACAO"
                )
        );
    }

    @Test
    void testAtualizar_ValorInvalido_ReservaNaoExiste() {
        Reserva reservaEsperada = new ReservaMock();
        StatusReserva novoStatusEsperado = StatusReserva.CANCELADA;

        when(reservaGateway.verificarSeExiste(reservaEsperada.getId())).thenReturn(false);

        assertThrows(
                IllegalArgumentException.class,
                () -> atualizaStatusReservaUseCase.atualizar(reservaEsperada.getId(), novoStatusEsperado.toString())
        );

        verify(reservaGateway, times(1)).verificarSeExiste(reservaEsperada.getId());
        verify(reservaGateway, never()).alterarStatus(reservaEsperada.getId(), novoStatusEsperado);
    }
}
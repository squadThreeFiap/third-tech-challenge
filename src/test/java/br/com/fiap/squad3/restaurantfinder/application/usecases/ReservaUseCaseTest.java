package br.com.fiap.squad3.restaurantfinder.application.usecases;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.StatusReserva;
import br.com.fiap.squad3.restaurantfinder.application.gateways.ReservaMesaGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.RestauranteGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaUseCaseTest {
    private ReservaUseCase reservaUseCase;

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private RestauranteGateway restauranteGateway;

    @Mock
    private ReservaMesaGateway reservaMesaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reservaUseCase = new ReservaUseCase(usuarioGateway, restauranteGateway, reservaMesaGateway);
    }

    @Test
    void testReservar_ValoresValidos_Success() {
        Long idUsuario = 1L;
        Long idRestaurante = 2L;
        Integer quantidadePessoas = 4;
        LocalDateTime dataHoraInicio = LocalDateTime.now().plusHours(1);
        LocalDateTime dataHoraFim = LocalDateTime.now().plusHours(2);

        when(usuarioGateway.verificarSeExistePeloId(idUsuario)).thenReturn(true);
        when(restauranteGateway.verificarSeExiste(idRestaurante)).thenReturn(true);
        when(reservaMesaGateway.verificarSeEstaDisponivelParaReservar(dataHoraInicio, dataHoraFim, quantidadePessoas)).thenReturn(true);

        Reserva reserva = reservaUseCase.cadastrar(idUsuario, idRestaurante, quantidadePessoas, dataHoraInicio, dataHoraFim);

        assertNotNull(reserva);
        assertEquals(StatusReserva.AGUARDANDO, reserva.getStatus());
    }

    @Test
    void testReservar_ValoresInvalidos_DiasDiferentes() {
        fail("Teste pendente de implementação.");
    }

    @Test
    void testReservar_ValoresInvalidos_HoraFinalAntesDaInicial() {
        fail("Teste pendente de implementação.");
    }

    void testReservar_ValoresInvalidos_PermanenciaMinimaInsuficiente() {
        fail("Teste pendente de implementação.");
    }

    void testReservar_ValoresInvalidos_PermanenciaMaximaExcedida() {
        fail("Teste pendente de implementação.");
    }

    void testReservar_ValoresInvalidos_UsuarioNaoExiste() {
        fail("Teste pendente de implementação.");
    }

    void testReservar_ValoresInvalidos_RestauranteNaoExiste() {
        fail("Teste pendente de implementação.");
    }

    void testReservar_ValoresInvalidos_HorarioIndisponivel() {
        fail("Teste pendente de implementação.");
    }

}

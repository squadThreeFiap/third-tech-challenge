package br.com.fiap.squad3.restaurantfinder.application.usecases;

import br.com.fiap.squad3.restaurantfinder.application.entities.ReservaDetalhada;
import br.com.fiap.squad3.restaurantfinder.application.gateways.ReservaGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.RestauranteGateway;
import mocks.ReservaDetalhadaMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class VizualizarReservasUseCaseTest {
    @Mock
    private RestauranteGateway restauranteGateway;

    @Mock
    private ReservaGateway reservaGateway;

    @InjectMocks
    private VizualizarReservasUseCase vizualizarReservasUseCase;

    private List<ReservaDetalhada> reservas;

    @BeforeEach
    public void setup() {
        restauranteGateway = mock(RestauranteGateway.class);
        reservaGateway = mock(ReservaGateway.class);
        vizualizarReservasUseCase = new VizualizarReservasUseCase(restauranteGateway, reservaGateway);

        reservas = List.of(new ReservaDetalhadaMock());
    }

    @Test
    public void testVisualizar_Sucesso() {
        Long idRestaurante = 10L;
        when(restauranteGateway.verificarSeExiste(idRestaurante)).thenReturn(true);
        when(reservaGateway.buscarDetalhesPeloIdDoRestaurante(idRestaurante)).thenReturn(reservas);

        List<ReservaDetalhada> result = vizualizarReservasUseCase.visualizar(idRestaurante);

        assertEquals(reservas, result);
        verify(restauranteGateway).verificarSeExiste(idRestaurante);
        verify(reservaGateway).buscarDetalhesPeloIdDoRestaurante(idRestaurante);
    }

    @Test
    public void testVisualizar_ValoreInvalido_RestauranteNaoExiste() {
        Long idRestaurante = 10L;
        when(restauranteGateway.verificarSeExiste(idRestaurante)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> vizualizarReservasUseCase.visualizar(idRestaurante));
        verify(restauranteGateway, times(1)).verificarSeExiste(idRestaurante);
        verify(reservaGateway, never()).buscarDetalhesPeloIdDoRestaurante(idRestaurante);
    }
}
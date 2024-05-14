package br.com.fiap.squad3.restaurantfinder.application.usecases;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.StatusReserva;
import br.com.fiap.squad3.restaurantfinder.application.gateways.ReservaGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.RestauranteGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.UsuarioGateway;
import mocks.ReservaMock;
import mocks.RestauranteMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Description;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CadastroReservaUseCaseTest {

    @InjectMocks
    private CadastroReservaUseCase cadastroReservaUseCase;

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private RestauranteGateway restauranteGateway;

    @Mock
    private ReservaGateway reservaGateway;

    private Reserva reserva;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cadastroReservaUseCase = new CadastroReservaUseCase(usuarioGateway, restauranteGateway, reservaGateway);

        LocalDateTime dataHoraInicio = LocalDateTime.now().plusHours(1);
        Reserva reservaValida = new Reserva(1L, 1L, 2, dataHoraInicio, dataHoraInicio.plusHours(1));
        reserva = reservaValida;
    }

    @Test
    void testReservar_ValoresValidos_Success() {
        when(usuarioGateway.verificarSeExistePeloId(reserva.getIdUsuario())).thenReturn(true);
        when(restauranteGateway.verificarSeExiste(reserva.getIdRestaurante())).thenReturn(true);

        Restaurante restaurante = new RestauranteMock();
        restaurante.setId(reserva.getIdRestaurante());

        when(restauranteGateway.buscarPeloId(reserva.getIdRestaurante())).thenReturn(restaurante);

        Long idReservaEsperado = 123L;
        StatusReserva statusEsperado = StatusReserva.AGENDADO;
        Reserva reservaCadastrada = new Reserva(
                idReservaEsperado,
                reserva.getIdUsuario(),
                reserva.getIdRestaurante(),
                reserva.getQuantidadePessoas(),
                reserva.getDataHoraInicio(),
                reserva.getDataHoraFim(),
                statusEsperado
        );
        when(reservaGateway.cadastrar(any(Reserva.class))).thenReturn(reservaCadastrada);

        Reserva result = cadastroReservaUseCase.cadastrar(reserva);

        assertNotNull(result);
        assertEquals(idReservaEsperado, result.getId());
        assertEquals(reserva.getIdUsuario(), result.getIdUsuario());
        assertEquals(reserva.getIdRestaurante(), result.getIdRestaurante());
        assertEquals(reserva.getQuantidadePessoas(), result.getQuantidadePessoas());
        assertEquals(reserva.getDataHoraInicio(), result.getDataHoraInicio());
        assertEquals(reserva.getDataHoraFim(), result.getDataHoraFim());
        assertEquals(statusEsperado, result.getStatus());
    }

    @Test
    void testReservar_ValoresInvalidos_DiasDiferentes() {
        LocalDateTime dataHoraInicial = LocalDateTime.now();

        reserva.setDataHoraInicio(dataHoraInicial);
        reserva.setDataHoraFim(dataHoraInicial.plusDays(1));  // Hora final no dia seguinte

        assertThrows(IllegalArgumentException.class, () -> cadastroReservaUseCase.cadastrar(reserva));
    }

    @Test
    void testReservar_ValoresInvalidos_HoraFinalAntesDaInicial() {
        LocalDateTime dataHoraInicial = LocalDateTime.now().plusHours(1);

        reserva.setDataHoraInicio(dataHoraInicial.plusHours(3)); // Início após o fim
        reserva.setDataHoraFim(dataHoraInicial);

        assertThrows(IllegalArgumentException.class, () -> cadastroReservaUseCase.cadastrar(reserva));
    }

    @Test
    void testReservar_ValoresInvalidos_PermanenciaMinimaInsuficiente() {
        LocalDateTime dataHoraInicial = LocalDateTime.now();

        reserva.setDataHoraInicio(dataHoraInicial);
        reserva.setDataHoraFim(dataHoraInicial.plusMinutes(10)); // Permanêcencia menor que a mínima

        assertThrows(IllegalArgumentException.class, () -> cadastroReservaUseCase.cadastrar(reserva));
    }

    @Test
    @Description("Este teste pode ocasionar erros de acordo com o horário em que for executado." +
            "Por somar uma quantidade considerável de horas na data final, visando garantir que a permanência máxima" +
            "está sendo validada, pode ser que a data final seja no dia seguinte, o que cairá em outra validação.")
    void testReservar_ValoresInvalidos_PermanenciaMaximaExcedida() {
        LocalDateTime dataHoraInicial = LocalDateTime.now();

        reserva.setDataHoraInicio(dataHoraInicial);
        Integer permanenciaEmExcesso = CadastroReservaUseCase.PERMANCENCIA_MAXIMA + 1;
        reserva.setDataHoraFim(dataHoraInicial.plusMinutes(permanenciaEmExcesso)); // Permanêcencia menor que a mínima

        assertThrows(IllegalArgumentException.class, () -> cadastroReservaUseCase.cadastrar(reserva));
    }

    @Test
    void testReservar_ValoresInvalidos_UsuarioNaoExiste() {
        when(usuarioGateway.verificarSeExistePeloId(reserva.getIdUsuario())).thenReturn(false);
        when(restauranteGateway.verificarSeExiste(reserva.getIdRestaurante())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> cadastroReservaUseCase.cadastrar(reserva));
    }

    @Test
    void testReservar_ValoresInvalidos_RestauranteNaoExiste() {
        when(usuarioGateway.verificarSeExistePeloId(reserva.getIdUsuario())).thenReturn(true);
        when(restauranteGateway.verificarSeExiste(reserva.getIdRestaurante())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> cadastroReservaUseCase.cadastrar(reserva));
    }

    @Test
    void testReservar_ValoresInvalidos_HorarioIndisponivelParaUsuario() {
        when(usuarioGateway.verificarSeExistePeloId(reserva.getIdUsuario())).thenReturn(true);
        when(restauranteGateway.verificarSeExiste(reserva.getIdRestaurante())).thenReturn(true);

        Reserva reservaDoUsuario = new ReservaMock();
        reservaDoUsuario.setIdUsuario(reserva.getIdUsuario()); // Reserva do mesmo usuario que está tentando reservar
        reservaDoUsuario.setDataHoraInicio(reserva.getDataHoraInicio());
        reservaDoUsuario.setDataHoraFim(reserva.getDataHoraFim());

        when(
                reservaGateway.obterReservasDoUsuarioNoHorarioAgendado(
                        reserva.getIdRestaurante(),
                        reserva.getDataHoraInicio(),
                        reserva.getDataHoraFim()
                )
        ).thenReturn(
                Arrays.asList(reservaDoUsuario) // Conflito de reservas
        );

        assertThrows(IllegalArgumentException.class, () -> cadastroReservaUseCase.cadastrar(reserva));
    }

    @Test
    void testReservar_ValoresInvalidos_HorarioIndisponivelParaRestaurante() {
        when(usuarioGateway.verificarSeExistePeloId(reserva.getIdUsuario())).thenReturn(true);
        when(restauranteGateway.verificarSeExiste(reserva.getIdRestaurante())).thenReturn(true);

        Restaurante restaurante = new RestauranteMock();
        restaurante.setId(reserva.getIdRestaurante());

        restaurante.setCapacidade(1); // Restaurante com capacidade de apenas 1

        when(restauranteGateway.buscarPeloId(reserva.getIdRestaurante())).thenReturn(restaurante);

        Long idReservaEsperado = 123L;
        StatusReserva statusEsperado = StatusReserva.AGENDADO;
        Reserva reservaCadastrada = new Reserva(
                idReservaEsperado,
                reserva.getIdUsuario(),
                reserva.getIdRestaurante(),
                reserva.getQuantidadePessoas(),
                reserva.getDataHoraInicio(),
                reserva.getDataHoraFim(),
                statusEsperado
        );
        when(reservaGateway.cadastrar(any(Reserva.class))).thenReturn(reservaCadastrada);

        when(
                reservaGateway.obterTodasAsReservasOcupadasNoHorarioAgendado(
                        reserva.getIdRestaurante(),
                        reserva.getDataHoraInicio(),
                        reserva.getDataHoraFim()
                )
        ).thenReturn(
                Arrays.asList(new ReservaMock()) // Restaurante lotado
        );

        assertThrows(IllegalArgumentException.class, () -> cadastroReservaUseCase.cadastrar(reserva));
    }
}

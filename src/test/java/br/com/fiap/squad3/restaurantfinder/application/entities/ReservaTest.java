package br.com.fiap.squad3.restaurantfinder.application.entities;

import br.com.fiap.squad3.restaurantfinder.application.entities.enums.StatusReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservaTest {

    private Reserva reserva;

    @BeforeEach
    void setUp() {
        reserva = new Reserva(
                1L,
                2L,
                3,
                LocalDateTime.now(),
                LocalDateTime.now(),
                StatusReserva.AGENDADO
        );
    }

    @Test
    void testSetIdUsuario_IdValido() {
        Long idUsuarioEsperado = 1L;

        Reserva reserva = new Reserva(
                idUsuarioEsperado,
                2L,
                3,
                LocalDateTime.now(),
                LocalDateTime.now(),
                StatusReserva.AGENDADO
        );

        assertEquals(idUsuarioEsperado, reserva.getIdUsuario());
    }

    @Test
    void testSetIdUsuario_IdInvalido() {
        assertThrows(IllegalArgumentException.class, () -> reserva.setIdUsuario(null));
        assertThrows(IllegalArgumentException.class, () -> reserva.setIdUsuario(0L));
        assertThrows(IllegalArgumentException.class, () -> reserva.setIdUsuario(-1L));
    }

    @Test
    void testSetIdRestaurante_IdValido() {
        Long idRestauranteEsperado = 20L;

        reserva.setIdRestaurante(idRestauranteEsperado);

        assertEquals(idRestauranteEsperado, reserva.getIdRestaurante());
    }

    @Test
    void testSetIdRestaurante_IdInvalido() {
        assertThrows(IllegalArgumentException.class, () -> reserva.setIdRestaurante(null));
        assertThrows(IllegalArgumentException.class, () -> reserva.setIdRestaurante(0L));
        assertThrows(IllegalArgumentException.class, () -> reserva.setIdRestaurante(-13L));
    }

    @Test
    void testSetQuantidadePessoas_ValorValido() {
        Integer quantidadePessoasEsperado = 5;

        reserva.setQuantidadePessoas(quantidadePessoasEsperado);

        assertEquals(quantidadePessoasEsperado, reserva.getQuantidadePessoas());
    }

    @Test
    void testSetQuantidadePessoas_ValorInvalido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> reserva.setQuantidadePessoas(Reserva.QUANTIDADE_PESSOAS_MINIMA - 1)
        );
    }

    @Test
    void testSetDataHoraInicio_ValorValido() {
        LocalDateTime dataHoraInicioEsperada = LocalDateTime.now().plusHours(1);

        reserva.setDataHoraInicio(dataHoraInicioEsperada);

        assertEquals(dataHoraInicioEsperada, reserva.getDataHoraInicio());
    }

    @Test
    void testSetDataHoraFim_ValorValido() {
        LocalDateTime dataHoraFimEsperada = LocalDateTime.now().plusHours(2);

        reserva.setDataHoraFim(dataHoraFimEsperada);

        assertEquals(dataHoraFimEsperada, reserva.getDataHoraFim());
    }
}

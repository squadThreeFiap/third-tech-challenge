package br.com.fiap.squad3.restaurantfinder.application.entities;

import br.com.fiap.squad3.restaurantfinder.application.entities.enums.StatusReserva;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservaDetalhadaTest {

    @Test
    public void testReservaDetalhada() {
        Long id = 1L;
        Usuario usuario = Mockito.mock(Usuario.class);
        int quantidadePessoas = 2;
        LocalDateTime dataHoraInicio = LocalDateTime.now().plusHours(1);
        LocalDateTime dataHoraFim = dataHoraInicio.plusHours(1);
        StatusReserva status = StatusReserva.AGENDADO;

        ReservaDetalhada reservaDetalhada = new ReservaDetalhada(id, usuario, quantidadePessoas, dataHoraInicio, dataHoraFim, status);

        assertEquals(id, reservaDetalhada.getId());
        assertEquals(usuario, reservaDetalhada.getUsuario());
        assertEquals(quantidadePessoas, reservaDetalhada.getQuantidadePessoas());
        assertEquals(dataHoraInicio, reservaDetalhada.getDataHoraInicio());
        assertEquals(dataHoraFim, reservaDetalhada.getDataHoraFim());
        assertEquals(status, reservaDetalhada.getStatus());
    }
}
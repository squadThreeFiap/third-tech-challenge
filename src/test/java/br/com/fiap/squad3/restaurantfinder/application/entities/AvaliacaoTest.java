package br.com.fiap.squad3.restaurantfinder.application.entities;

import mocks.AvaliacaoMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TextoUtil;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AvaliacaoTest {
    private Avaliacao avaliacao;

    Long id = 10L;
    Long idReserva = 20L;
    short nota = 3;
    String comentario = "Este é um comentário válido.";

    @BeforeEach
    void setUp() {
        avaliacao = new AvaliacaoMock();
    }

    @Test
    void testConstrutor2() {
        Avaliacao avaliacaoConstrutor2 = new Avaliacao(idReserva, nota, comentario);

        assertEquals(idReserva, avaliacaoConstrutor2.getIdReserva());
        assertEquals(nota, avaliacaoConstrutor2.getNota());
        assertEquals(comentario, avaliacaoConstrutor2.getComentario());
    }

    @Test
    void testConstrutor3() {
        Avaliacao avaliacaoConstrutor3 = new Avaliacao(id, idReserva, nota, comentario);

        assertEquals(id, avaliacaoConstrutor3.getId());
        assertEquals(idReserva, avaliacaoConstrutor3.getIdReserva());
        assertEquals(nota, avaliacaoConstrutor3.getNota());
        assertEquals(comentario, avaliacaoConstrutor3.getComentario());
    }

    @Test
    void testSetIdReserva_IdValido() {
        avaliacao.setIdReserva(idReserva);

        assertEquals(idReserva, avaliacao.getIdReserva());
    }

    @Test
    void testSetIdReserva_ValorInvalidoId() {
        assertThrows(IllegalArgumentException.class, () -> avaliacao.setIdReserva(null));
        assertThrows(IllegalArgumentException.class, () -> avaliacao.setIdReserva(0L));
        assertThrows(IllegalArgumentException.class, () -> avaliacao.setIdReserva(-1L));
    }

    @Test
    void testSetNota_IntervaloValido() {
        avaliacao.setNota(nota);

        assertEquals(nota, avaliacao.getNota());
    }

    @Test
    void testSetNota_IntervaloInvalido() {
        assertThrows(IllegalArgumentException.class, () -> avaliacao.setNota((short) (Avaliacao.NOTA_MINIMA - 1)));
        assertThrows(IllegalArgumentException.class, () -> avaliacao.setNota((short) (Avaliacao.NOTA_MAXIMA + 1)));
    }

    @Test
    void testSetComentario_TamanhoValido() {
        avaliacao.setComentario(comentario);

        assertEquals(comentario, avaliacao.getComentario());
    }

    @Test
    void testSetComentario_TamanhoExedente() {
        String comentarioLongo = TextoUtil.criaTextoRandomico(Avaliacao.TAMANHO_MAXIMO_COMENTARIO + 1);

        assertThrows(IllegalArgumentException.class, () -> avaliacao.setComentario(comentarioLongo));
    }
}
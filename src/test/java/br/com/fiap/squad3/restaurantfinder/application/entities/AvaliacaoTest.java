package br.com.fiap.squad3.restaurantfinder.application.entities;

import mocks.AvaliacaoMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AvaliacaoTest {
    private Avaliacao avaliacao;

    @BeforeEach
    void setUp() {
        avaliacao = new AvaliacaoMock();
    }


    @Test
    void testSetIdReserva_IdValido() {
        Long idReservaEsperada = 20L;

        avaliacao.setIdReserva(idReservaEsperada);

        assertEquals(idReservaEsperada, avaliacao.getIdReserva());
    }

    @Test
    void testSetIdReserva_ValorInvalidoId() {
        assertThrows(IllegalArgumentException.class, () -> avaliacao.setIdReserva(null));
        assertThrows(IllegalArgumentException.class, () -> avaliacao.setIdReserva(0L));
        assertThrows(IllegalArgumentException.class, () -> avaliacao.setIdReserva(-1L));
    }

    @Test
    void testSetNota_IntervaloValido() {
        short notaEsperada = 3;

        avaliacao.setNota(notaEsperada);

        assertEquals(notaEsperada, avaliacao.getNota());
    }

    @Test
    void testSetNota_IntervaloInvalido() {
        assertThrows(IllegalArgumentException.class, () -> avaliacao.setNota((short) 0));
        assertThrows(IllegalArgumentException.class, () -> avaliacao.setNota((short) 6));
    }

    @Test
    void testSetComentario_TamanhoValido() {
        String comentarioEsperado = "Este é um comentário válido.";

        avaliacao.setComentario(comentarioEsperado);

        assertEquals(comentarioEsperado, avaliacao.getComentario());
    }

    @Test
    void testSetComentario_TamanhoExedente() {
        String comentarioLongo = criaTextoRandomico(avaliacao.TAMANHO_MAXIMO_COMENTARIO + 1);

        assertThrows(IllegalArgumentException.class, () -> avaliacao.setComentario(comentarioLongo));
    }

    private String criaTextoRandomico(Integer tamanhoTexto) {
        String caracteres = "   abcdefghijklm   nopqrstuvxwyz   ";
        Random random = new Random();
        String resultado = "";

        while (resultado.length() < tamanhoTexto) {
            char caractereRandom = caracteres.charAt(random.nextInt(caracteres.length()));
            resultado += caractereRandom;
        }
        return resultado;
    }
}
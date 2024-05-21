package br.com.fiap.squad3.restaurantfinder.application.entities;

import br.com.fiap.squad3.restaurantfinder.application.entities.Funcionamento;
import br.com.fiap.squad3.restaurantfinder.application.entities.Localizacao;
import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import mocks.FuncionamentoMock;
import mocks.LocalizacaoMock;
import mocks.RestauranteMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestauranteTest {
    Restaurante restaurante = new RestauranteMock();

    private Long id = 1L;
    private String nome = "Restaurante Teste";
    private String tipoCozinha = "Italiana";
    private Integer capacidade = 10;
    private Localizacao localizacao = new LocalizacaoMock();
    private Funcionamento funcionamento = new FuncionamentoMock();

    @Test
    void testGettersAndSetters() {
        restaurante.setId(id);
        restaurante.setNome(nome);
        restaurante.setTipoCozinha(tipoCozinha);
        restaurante.setCapacidade(capacidade);
        restaurante.setLocalizacao(localizacao);
        restaurante.setFuncionamento(funcionamento);

        assertEquals(id, restaurante.getId());
        assertEquals(nome, restaurante.getNome());
        assertEquals(tipoCozinha, restaurante.getTipoCozinha());
        assertEquals(capacidade, restaurante.getCapacidade());
        assertEquals(localizacao, restaurante.getLocalizacao());
        assertEquals(funcionamento, restaurante.getFuncionamento());
    }

    @Test
    void setNomeInvalido() {
        String mensagemEsperada = "Nome deve ser informado.";
        assertThrows(IllegalArgumentException.class, () -> restaurante.setNome(""), mensagemEsperada);
        assertThrows(IllegalArgumentException.class, () -> restaurante.setNome(null), mensagemEsperada);
    }

    @Test
    void setTipoCozinhaComFalha() {
        String mensagemEsperada = "Tipo de cozinha deve ser informada.";

        assertThrows(IllegalArgumentException.class, () -> restaurante.setTipoCozinha(""), mensagemEsperada);
        assertThrows(IllegalArgumentException.class, () -> restaurante.setTipoCozinha(null), mensagemEsperada);
    }

    @Test
    void setCapacidadeComFalha() {
        String mensagemEsperada = "Capacidade mínima é " + Restaurante.CAPACIDADE_MINIMA + ".";

        assertThrows(IllegalArgumentException.class, () -> restaurante.setCapacidade(0), mensagemEsperada);
    }
}
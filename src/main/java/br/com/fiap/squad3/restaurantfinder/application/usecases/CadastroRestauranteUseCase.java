package br.com.fiap.squad3.restaurantfinder.application.usecases;

import br.com.fiap.squad3.restaurantfinder.application.entities.Funcionamento;
import br.com.fiap.squad3.restaurantfinder.application.entities.Localizacao;
import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.application.gateways.RestauranteGateway;
import org.springframework.http.HttpStatus;

import java.time.Duration;

public class CadastroRestauranteUseCase {
    private static final int TEMPO_FUNCIONAMENTO_MINIMO = 3;

    private RestauranteGateway restauranteGateway;

    public CadastroRestauranteUseCase(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public Restaurante cadastrar(Restaurante restaurante) {
        validarDadosRestaurante(restaurante);
        validarDadosLocalizacao(restaurante.getLocalizacao());
        validarDadosFuncionamento(restaurante.getFuncionamento());

        return this.restauranteGateway.cadastrar(restaurante);
    }

    private static void validarDadosRestaurante(Restaurante restaurante) {
        if (restaurante.getNome().trim().isEmpty() || restaurante.getNome().trim().isBlank()) {
            throw new IllegalArgumentException("Nome do restaurante deve ser informado.");
        }
    }

    private static void validarDadosLocalizacao(Localizacao localizacao) {
        if (localizacao.getNumero() != null && localizacao.getNumero() < 0) {
            throw new IllegalArgumentException("O número da localização, quando informado, deve ser positivo.");
        }
    }

    private static void validarDadosFuncionamento(Funcionamento funcionamento) {
        if (funcionamento.getHoraAbertura().isAfter(funcionamento.getHoraEncerramento())) {
            throw new IllegalArgumentException("Horário de abertura deve ser antes do horário de encerramento.");
        }

        int tempoFuncionamento = Duration.between(
                funcionamento.getHoraAbertura(),
                funcionamento.getHoraEncerramento()
        ).toHoursPart();

        String fimDaFraseComPluralCorreto = (TEMPO_FUNCIONAMENTO_MINIMO > 1 ? "s." : ".");

        if (tempoFuncionamento < TEMPO_FUNCIONAMENTO_MINIMO) {
            throw new IllegalArgumentException("Tempo de funcionamento mínimo deve ser de pelo menos "
                    + TEMPO_FUNCIONAMENTO_MINIMO + " hora" + fimDaFraseComPluralCorreto);
        }
    }
}
package br.com.fiap.squad3.restaurantfinder.application.usecases;

import br.com.fiap.squad3.restaurantfinder.application.entities.ReservaDetalhada;
import br.com.fiap.squad3.restaurantfinder.application.gateways.ReservaGateway;
import br.com.fiap.squad3.restaurantfinder.application.gateways.RestauranteGateway;

import java.util.List;

public class GerenciamentoRestauranteUseCase {
    private final RestauranteGateway restauranteGateway;
    private final ReservaGateway reservaGateway;

    public GerenciamentoRestauranteUseCase(RestauranteGateway restauranteGateway, ReservaGateway reservaGateway) {
        this.restauranteGateway = restauranteGateway;
        this.reservaGateway = reservaGateway;
    }

    public List<ReservaDetalhada> visualizar(Long idRestaurante) {
        validaSeRestauranteExiste(idRestaurante);

        return reservaGateway.buscarDetalhesPeloIdDoRestaurante(idRestaurante);
    }

    private void validaSeRestauranteExiste(Long idRestaurante) {
        if (!restauranteGateway.verificarSeExiste(idRestaurante)) {
            throw new IllegalArgumentException("Restaurante não encontrado.");
        }
    }
}

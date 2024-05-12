package br.com.fiap.squad3.restaurantfinder.application.gateways;

import br.com.fiap.squad3.restaurantfinder.application.entities.Avaliacao;

public interface AvaliacaoGateway {
    Avaliacao cadastrar(Avaliacao avaliacao);

    Avaliacao buscarPeloIdDaReserva(Long id);
}

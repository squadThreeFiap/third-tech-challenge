package br.com.fiap.squad3.restaurantfinder.application.gateways;

import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;

public interface RestauranteGateway {
    Restaurante cadastrar(Restaurante restaurante);
}

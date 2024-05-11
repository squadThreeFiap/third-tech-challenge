package mocks;

import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;

public class RestauranteMock extends Restaurante {
    public RestauranteMock() {
        super(1L, "Restaurante", "Tipo da Cozinha", 100);
    }
}

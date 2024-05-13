package mocks;

import br.com.fiap.squad3.restaurantfinder.application.entities.Usuario;

import java.time.LocalDate;

public class UsuarioMock extends Usuario {
    public UsuarioMock() {
        super(
                1L,
                "12345678900",
                "Fulano",
                "11",
                "987654321",
                "fulano@example.com",
                LocalDate.now().minusDays(3)
        );
    }
}

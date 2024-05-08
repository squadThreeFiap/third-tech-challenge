package br.com.fiap.squad3.restaurantfinder.application.gateways;

import br.com.fiap.squad3.restaurantfinder.application.entities.Usuario;

public interface UsuarioGateway {
    Boolean verificarSeExiste(String cpf);

    Usuario cadastrar(Usuario restaurante);
}

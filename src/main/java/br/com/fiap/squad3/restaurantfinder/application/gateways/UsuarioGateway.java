package br.com.fiap.squad3.restaurantfinder.application.gateways;

import br.com.fiap.squad3.restaurantfinder.application.entities.Usuario;

public interface UsuarioGateway {
    Usuario cadastrar(Usuario restaurante);

    Boolean verificarSeExistePeloCpf(String cpf);

    Boolean verificarSeExistePeloId(Long id);
}

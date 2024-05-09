package br.com.fiap.squad3.restaurantfinder.application.usecases;

import br.com.fiap.squad3.restaurantfinder.application.entities.Usuario;
import br.com.fiap.squad3.restaurantfinder.application.gateways.UsuarioGateway;

public class CadastroUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;

    public CadastroUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Usuario cadastrar(Usuario usuario) {
        if (usuarioGateway.verificarSeExiste(usuario.getCpf())) {
            throw new IllegalArgumentException("Usuário com CPF " + usuario.getCpf() + " já existe.");
        }

        return this.usuarioGateway.cadastrar(usuario);
    }
}
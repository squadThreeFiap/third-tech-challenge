package br.com.fiap.squad3.restaurantfinder.application.usecases;

import br.com.fiap.squad3.restaurantfinder.application.entities.Usuario;
import br.com.fiap.squad3.restaurantfinder.application.gateways.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CadastroUsuarioUseCaseTest {
    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private CadastroUsuarioUseCase cadastroUsuarioUseCase;

    @BeforeEach
    void setUp() {
        usuarioGateway = mock(UsuarioGateway.class);
        cadastroUsuarioUseCase = new CadastroUsuarioUseCase(usuarioGateway);
    }

    @Test
    void testCadastrarNovoUsuario() {
        Usuario novoUsuario = new Usuario(null, "12345678900", "Fulano", "11", "987654321", "fulano@example.com", LocalDate.now());

        when(usuarioGateway.verificarSeExistePeloCpf(novoUsuario.getCpf())).thenReturn(false);
        when(usuarioGateway.cadastrar(novoUsuario)).thenReturn(novoUsuario);

        Usuario resultado = cadastroUsuarioUseCase.cadastrar(novoUsuario);

        verify(usuarioGateway, times(1)).verificarSeExistePeloCpf(novoUsuario.getCpf());
        verify(usuarioGateway, times(1)).cadastrar(novoUsuario);
    }

    @Test
    void testCadastrarUsuarioExistente() {
        Usuario usuarioExistente = new Usuario(1L, "12345678900", "Fulano", "11", "987654321", "fulano@example.com", LocalDate.now());

        when(usuarioGateway.verificarSeExistePeloCpf(usuarioExistente.getCpf())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> cadastroUsuarioUseCase.cadastrar(usuarioExistente));

        verify(usuarioGateway, times(1)).verificarSeExistePeloCpf(usuarioExistente.getCpf());
    }
}

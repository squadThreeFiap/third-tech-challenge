package br.com.fiap.squad3.restaurantfinder.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.EnumSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.fiap.squad3.restaurantfinder.application.entities.Funcionamento;
import br.com.fiap.squad3.restaurantfinder.application.entities.Localizacao;
import br.com.fiap.squad3.restaurantfinder.application.entities.Restaurante;
import br.com.fiap.squad3.restaurantfinder.application.entities.enums.DiaSemana;
import br.com.fiap.squad3.restaurantfinder.application.gateways.RestauranteGateway;
import br.com.fiap.squad3.restaurantfinder.application.usecases.CadastroRestauranteUseCase;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.RestauranteRepository;

class CadastroRestauranteUseCaseTest {

	 @Mock
	    private RestauranteGateway restauranteGateway;

	    @InjectMocks
	    private CadastroRestauranteUseCase cadastroRestauranteUseCase;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testCadastrarComSucesso() {
	        Restaurante restaurante = new Restaurante(1L, "Restaurante Teste", "Italiana", 10,
	                new Localizacao("06150347", "SP", "Osasco", "Santa Maria", "Rua Teste", 123),
	                new Funcionamento(EnumSet.allOf(DiaSemana.class), LocalTime.of(10, 0), LocalTime.of(22, 0)));

	        when(restauranteGateway.cadastrar(any(Restaurante.class))).thenReturn(restaurante);

	        Restaurante resultado = cadastroRestauranteUseCase.cadastrar(restaurante);

	        assertNotNull(resultado);
	        assertEquals("Restaurante Teste", resultado.getNome());
	        verify(restauranteGateway, times(1)).cadastrar(any(Restaurante.class));
	    }

	    @Test
	    void testCadastrarComDadosInvalidosDeLocalizacao() {
	        // Adjusted to provide an explicitly invalid location number
	        Localizacao localizacao = new Localizacao("06150347", "SP", "Osasco", "Santa Maria", "Rua Teste", -123); // Note the negative number
	        Funcionamento funcionamentoDummy = new Funcionamento(EnumSet.of(DiaSemana.SEGUNDA), LocalTime.of(10, 0), LocalTime.of(22, 0));
	        Restaurante restaurante = new Restaurante(1L, "Restaurante Teste", "Italiana", 10, localizacao, funcionamentoDummy);

	        Exception exception = assertThrows(IllegalArgumentException.class, () -> cadastroRestauranteUseCase.cadastrar(restaurante));

	        assertEquals("O número da localização, quando informado, deve ser positivo.", exception.getMessage());
	    }
	    
	    @Test
	    void testCadastrarComDadosInvalidosDeFuncionamento() {
	        Localizacao localizacaoDummy =  new Localizacao("06150347", "SP", "Osasco", "Santa Maria", "Rua Teste", 123);
	        Funcionamento funcionamento = new Funcionamento(EnumSet.allOf(DiaSemana.class), LocalTime.of(22, 0), LocalTime.of(10, 0)); // Adjusted times to trigger the expected IllegalArgumentException
	        Restaurante restaurante = new Restaurante(1L, "Restaurante Teste", "Italiana", 10, localizacaoDummy, funcionamento);

	        Exception exception = assertThrows(IllegalArgumentException.class, () -> cadastroRestauranteUseCase.cadastrar(restaurante));

	        assertEquals("Horário de abertura deve ser antes do horário de encerramento.", exception.getMessage());
	    }
}
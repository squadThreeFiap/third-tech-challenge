package br.com.fiap.squad3.restaurantfinder.controller;

import br.com.fiap.squad3.restaurantfinder.application.entities.enums.DiaSemana;
import br.com.fiap.squad3.restaurantfinder.application.usecases.CadastroRestauranteUseCase;
import br.com.fiap.squad3.restaurantfinder.application.usecases.VizualizarReservasUseCase;
import br.com.fiap.squad3.restaurantfinder.external.restapi.controllers.RestauranteController;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteRequestDto;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.ReservaDtoConverter;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.RestauranteDtoConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;
import java.util.Collections;

public class RestauranteControllerTest {

    private MockMvc mockMvc;

    AutoCloseable openMocks;
    @Mock
    private  CadastroRestauranteUseCase cadastroRestauranteUseCase;
    @Mock
    private  VizualizarReservasUseCase vizualizarReservasUseCase;
    @Mock
    private  RestauranteDtoConverter restauranteDtoConverter;
    @Mock
    private  ReservaDtoConverter reservaDtoConverter;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        RestauranteController restauranteController = new RestauranteController(cadastroRestauranteUseCase,vizualizarReservasUseCase,restauranteDtoConverter,reservaDtoConverter);
        mockMvc = MockMvcBuilders.standaloneSetup(restauranteController).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }


    @Test
    void devePermitirCadastrarRestaurante(){
        var restaurante = new RestauranteRequestDto(null,"Danilo Teste", "Nordestina", 111, "07104020", "teste",  "BR",  "Guarulhos", "vila", Collections.singleton(DiaSemana.SEGUNDA), LocalTime.now(), LocalTime.now(), 10);

//      when(cadastroRestauranteUseCase.cadastrar(any(Restaurante.class)))
   }

    @Test
    void devePermitirLocalizarReservasDoRestaurante(){
        Assertions.fail("Teste nao implementado");
    }

    @Test
    void devePermitirLocalizarReservasDoRestaurantePaginado(){
        Assertions.fail("Teste nao implementado");
    }
}

package br.com.fiap.squad3.restaurantfinder.controller;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.usecases.AtualizaStatusReservaUseCase;
import br.com.fiap.squad3.restaurantfinder.application.usecases.CadastroReservaUseCase;
import br.com.fiap.squad3.restaurantfinder.external.restapi.controllers.ReservaController;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaRequestDto;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.ReservaDtoConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReservaControllerTest {

    private MockMvc mockMvc;

    AutoCloseable openMocks;

    @Mock
    private CadastroReservaUseCase cadastroReservaUseCase;
    @Mock
    private AtualizaStatusReservaUseCase atualizaStatusReservaUseCase;
    @Mock
    private ReservaDtoConverter reservaDtoConverter;

    @BeforeEach
    void setUp(){
        openMocks = MockitoAnnotations.openMocks(this);
        ReservaController reservaController = new ReservaController(cadastroReservaUseCase,atualizaStatusReservaUseCase,reservaDtoConverter);
        mockMvc = MockMvcBuilders.standaloneSetup(reservaController).build();
    }

    @AfterEach
    void tearDown() throws Exception{
        openMocks.close();
    }

    @Test
    void devePermitirFazerReserva() throws Exception {
        var reserva = new ReservaRequestDto(1L,1L,10, LocalDateTime.now(), LocalDateTime.now());
        when(cadastroReservaUseCase.cadastrar(any(Reserva.class)))
                .thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(post("/reserva")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(reserva)))
                .andExpect(status().isCreated());
        verify(cadastroReservaUseCase, times(1)).cadastrar(any(Reserva.class));
    }

    @Test
    void devePermitiraAtualizarStatus(){
        fail("Teste nao implementado");
    }

    public static String asJsonString(final Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(object);
    }
}

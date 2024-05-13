package br.com.fiap.squad3.restaurantfinder.controller;

import br.com.fiap.squad3.restaurantfinder.application.entities.Usuario;
import br.com.fiap.squad3.restaurantfinder.application.usecases.CadastroUsuarioUseCase;
import br.com.fiap.squad3.restaurantfinder.external.restapi.controllers.UsuarioController;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api.UsuarioDtoConverter;
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

import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.UsuarioRequestDto;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UsuarioControllerTest {

    private MockMvc mockMvc;

    AutoCloseable openMocks;

    @Mock
    private CadastroUsuarioUseCase cadastroUsuarioUseCase;
    @Mock
    private UsuarioDtoConverter usuarioDtoConverter;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        UsuarioController usuarioController = new UsuarioController(cadastroUsuarioUseCase,usuarioDtoConverter);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCadastrarUsuario() throws Exception {
        var usuario = novoUsuario();
        when(cadastroUsuarioUseCase.cadastrar(any(Usuario.class)))
                .thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(usuario)))
                .andExpect(status().isCreated());
        verify(cadastroUsuarioUseCase, times(1)).cadastrar(any(Usuario.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarUsuarioComCpfExistente(){
        fail("Teste nao implementado");
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarUsuarioSemCpf(){
        fail("Teste nao implementado");
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarUsuarioComCpfInvalido(){
        fail("Teste nao implementado");
    }

    static UsuarioRequestDto novoUsuario() {
       return new UsuarioRequestDto("18316001020", "Teste de agora", "11", "98765432", "fulano@example.com", LocalDate.now());
    }

    public static String asJsonString(final Object object) throws JsonProcessingException {
       ObjectMapper mapper = new ObjectMapper();
       mapper.registerModule(new JavaTimeModule());
       return mapper.writeValueAsString(object);
    }
}

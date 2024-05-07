package br.com.fiap.squad3.restaurantfinder.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import br.com.fiap.squad3.restaurantfinder.external.restapi.controllers.UsuarioController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.UsuarioDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.services.UsuarioService;

public class UsuarioEntityControllerTest {

    private MockMvc mockMvc;
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = mock(UsuarioService.class);
        UsuarioController usuarioController = new UsuarioController(usuarioService);
        mockMvc = standaloneSetup(usuarioController).build();
    }

    @Test
    void saveUsuario_WhenValidInput_ReturnsCreated() throws Exception {
        UsuarioDto usuarioDto = new UsuarioDto(null, "33014076090", "Restaurant Finder", "11", "987654321", "contato@restaurantfinder.com", null);
        when(usuarioService.save(any(UsuarioDto.class))).thenReturn(usuarioDto);

        mockMvc.perform(post("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"cpf\":\"33014076090\",\"nome\":\"Restaurant Finder\",\"ddd\":\"11\",\"telefone\":\"987654321\",\"email\":\"contato@restaurantfinder.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpf").value("33014076090"));
    }

    @Test
    void saveUsuario_WhenInvalidInput_ReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"cpf\":\"invalid\",\"nome\":\"\",\"ddd\":\"11\",\"telefone\":\"987654321\",\"email\":\"teste@teste.com\"}"))
                .andExpect(status().isBadRequest());
    }
}
package br.com.fiap.squad3.restaurantfinder.controller;

import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.UsuarioRequestDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerITTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    @Test
    void devePermitirCadastrarUsuario() throws Exception {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(novoUsuario())
        .when()
                .post("/usuario")
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarUsuarioComCpfExistente(){
        var usuario = new UsuarioRequestDto("12345678901", "Teste de agora", "11", "98765432", "fulano@example.com", LocalDate.now());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(usuarioExistente())
        .when()
                .post("/usuario")
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarUsuarioSemCpf(){
        var usuario = new UsuarioRequestDto(null, "Teste de agora", "11", "98765432", "fulano@example.com", LocalDate.now());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(usuario)
                .when()
                .post("/usuario")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarUsuarioComCpfInvalido(){
        var usuario = new UsuarioRequestDto("000000000", "Teste de agora", "11", "98765432", "fulano@example.com", LocalDate.now());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(usuario)
                .when()
                .post("/usuario")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    static UsuarioRequestDto novoUsuario() {
        return new UsuarioRequestDto("18316001020", "Teste de agora", "11", "98765432", "fulano@example.com", LocalDate.now());
    }

    static UsuarioRequestDto usuarioExistente() {
        return new UsuarioRequestDto("12345678901", "Teste de agora", "11", "98765432", "fulano@example.com", LocalDate.now());
    }

}

package br.com.fiap.squad3.restaurantfinder.controller;

import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.AtualizaStatusReservaRequestDto;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.ReservaRequestDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservaControllerITTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirFazerReserva() throws Exception {
        var reserva = new ReservaRequestDto( 1105L, 2020L, 10, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(reserva)
                .when()
                .post("/reserva")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void devePermitiraAtualizarStatus(){
        var reserva = new AtualizaStatusReservaRequestDto("CONCLUIDA");
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(reserva)
                .when()
                .patch("/reserva/{id}/status",300)
                .then()
                .statusCode(HttpStatus.ACCEPTED.value());
    }

    @Test
    void geraExcecao_quandoPassarStatusErrado(){
        var reserva = new AtualizaStatusReservaRequestDto("Teste");
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(reserva)
                .when()
                .patch("/reserva/{id}/status",300)
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void deveGerarExcecao_quandoIdIncorreto() throws Exception {
        var reserva = new ReservaRequestDto(null, 1L, 10, LocalDateTime.now(), LocalDateTime.now());
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(reserva)
                .when()
                .post("/reserva")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void deveGerarExcecao_quandoIdNaoExiste() throws Exception {
        var reserva = new ReservaRequestDto(1L, 1L, 10, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(reserva)
                .when()
                .post("/reserva")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


    @Test
    void deveGerarExcecao_quandoHoraInicioErrada() throws Exception {
        var reserva = new ReservaRequestDto(1L, 1L, 10, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(reserva)
                .when()
                .post("/reserva")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void deveGerarExcecao_quandoHoraFimErrada() throws Exception {
        var reserva = new ReservaRequestDto(1L, 1L, 10, LocalDateTime.now().plusHours(1), LocalDateTime.now());
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(reserva)
                .when()
                .post("/reserva")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void deveGerarExcecao_quandoReservarDiasDiferentes() throws Exception {
        var reserva = new ReservaRequestDto(1L, 1L, 10, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(8));
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(reserva)
                .when()
                .post("/reserva")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}

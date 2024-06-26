package br.com.fiap.squad3.restaurantfinder.controller;

import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.AvaliacaoRequestDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AvaliacaoControllerITTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirRegistrarAvaliacao(){
        var avaliacao = new AvaliacaoRequestDto(1L, (short) 5, "bom");
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(avaliacao)
                .when()
                .post("/avaliacao")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}

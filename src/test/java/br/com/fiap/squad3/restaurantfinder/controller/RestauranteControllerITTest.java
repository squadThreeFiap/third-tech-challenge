package br.com.fiap.squad3.restaurantfinder.controller;

import br.com.fiap.squad3.restaurantfinder.application.entities.enums.DiaSemana;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteRequestDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestauranteControllerITTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirCadastrarRestaurante() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        var restaurante = new RestauranteRequestDto(null, "Danilo Teste", "Nordestina", 111, "07104020", "teste", "BR", "Guarulhos", "vila", Collections.singleton(DiaSemana.SEGUNDA), LocalTime.parse("10:00:00", formatter), LocalTime.parse("13:00:00", formatter), 10);
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restaurante)
                .when()
                .post("/restaurante")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void devePermitirLocalizarReservasDoRestaurante() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/restaurante/{id}/reservas", 1010 )
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void devePermitirLocalizarReservasDoRestaurantePaginado_comParametros() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("pagina", "2")
                .queryParam("numeroItensPorPagina", "2")
                .queryParam("ordenarPor", "")
                .queryParam("ordemCrescente", "true")
                .when()
                .get("/restaurante/{id}/reservas/paginacao", 1010 )
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void devePermitirLocalizarReservasDoRestaurantePaginado_semParametros() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/restaurante/{id}/reservas/paginacao", 1010 )
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void deveGerarExcecao_seTempoMinimoMenor3horas() {
        var restaurante = new RestauranteRequestDto(null, "Danilo Teste", "Nordestina", 111, "07104020", "teste", "BR", "Guarulhos", "vila", Collections.singleton(DiaSemana.SEGUNDA), LocalTime.now(), LocalTime.now(), 10);
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restaurante)
                .when()
                .post("/restaurante")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void deveGerarExcecao_seNomeVazio() {
        var restaurante = new RestauranteRequestDto(null, "", "Nordestina", 111, "07104020", "teste", "BR", "Guarulhos", "vila", Collections.singleton(DiaSemana.SEGUNDA), LocalTime.now(), LocalTime.now().plusHours(3), 10);
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restaurante)
                .when()
                .post("/restaurante")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}

package br.com.fiap.squad3.restaurantfinder.external.restapi.controllers.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateMessage {
    private String campo;
    private String mensagem;
}
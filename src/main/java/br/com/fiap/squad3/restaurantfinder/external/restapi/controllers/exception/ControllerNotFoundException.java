package br.com.fiap.squad3.restaurantfinder.external.restapi.controllers.exception;

public class ControllerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ControllerNotFoundException(String exception) {
        super(exception);
    }
}

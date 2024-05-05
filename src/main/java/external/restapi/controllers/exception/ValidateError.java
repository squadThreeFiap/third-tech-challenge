package external.restapi.controllers.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidateError extends StandardError {
    private static final long serialVersionUID = 1L;

    private List<ValidateMessage> mensagens = new ArrayList<ValidateMessage>();

    public void addMensagens(String campo, String mensagem) {
        mensagens.add(new ValidateMessage(campo, mensagem));
    }
}
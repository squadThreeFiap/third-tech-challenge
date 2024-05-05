package external.restapi.controller.exception;

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
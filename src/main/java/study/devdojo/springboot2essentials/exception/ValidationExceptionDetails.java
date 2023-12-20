package study.devdojo.springboot2essentials.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Classe ValidationExceptionDetails.
 * Esta classe representa os detalhes específicos das exceções relacionadas à validação.
 * Ela herda de ExceptionDetails, uma classe abstrata que contém informações comuns sobre exceções.
 * É utilizada para encapsular detalhes adicionais de exceções de validação, como campos inválidos
 * e as respectivas mensagens de erro relacionadas a eles.
 */
@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
    private String fields; // Representa os campos inválidos que causaram a exceção.
    private String fieldsMessage; // Mensagens de erro relacionadas aos campos inválidos.
}

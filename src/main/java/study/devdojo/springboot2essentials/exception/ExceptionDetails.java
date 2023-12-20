package study.devdojo.springboot2essentials.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Classe ExceptionDetails.
 * Esta classe representa os detalhes comuns das exceções da aplicação.
 * Contém informações essenciais sobre exceções, como título, código de status HTTP, detalhes adicionais,
 * mensagem para desenvolvedores e marcação de tempo.
 * Utilizada como uma classe base para outras classes de detalhes específicos de exceção, como BadRequestExceptionDetails,
 * ValidationExceptionDetails, ObjectNotFoundExceptionDetails, entre outras, que herdam campos desta classe.
 * Contém campos genéricos compartilhados entre diferentes tipos de exceções da aplicação,
 * facilitando a padronização e reutilização de informações comuns em várias exceções.
 */
@Data
@SuperBuilder
public class ExceptionDetails {
    protected String title; // Título ou descrição resumida da exceção.
    protected int status; // Código de status HTTP associado à exceção.
    protected String details; // Detalhes ou informações adicionais sobre a exceção.
    protected String developerMessage; // Mensagem para desenvolvedores sobre a exceção.
    protected LocalDateTime timestamp; // Marcação de tempo da ocorrência da exceção.
}

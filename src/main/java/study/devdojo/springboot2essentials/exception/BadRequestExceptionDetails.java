package study.devdojo.springboot2essentials.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Representa os detalhes de uma exceção do tipo BadRequestException.
 * Utilizada para encapsular informações sobre a exceção.
 */
@Data
@Builder
public class BadRequestExceptionDetails {
    private String title; // Título ou descrição resumida da exceção.
    private int status; // Código de status HTTP associado à exceção.
    private String details; // Detalhes ou informações adicionais sobre a exceção.
    private String developerMessage; // Mensagem para desenvolvedores sobre a exceção.
    private LocalDateTime timestamp; // Marcação de tempo da ocorrência da exceção.
}

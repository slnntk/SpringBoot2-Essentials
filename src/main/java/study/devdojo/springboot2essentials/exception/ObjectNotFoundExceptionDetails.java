package study.devdojo.springboot2essentials.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Classe ObjectNotFoundExceptionDetails.
 * Esta classe representa os detalhes específicos das exceções de objeto não encontrado.
 * Ela herda de ExceptionDetails, uma classe abstrata que contém informações comuns sobre exceções.
 * Esta classe em particular não possui campos adicionais além dos herdados de ExceptionDetails,
 * mas pode ser estendida para incluir informações extras sobre exceções específicas de objeto não encontrado, se necessário no futuro.
 */
@Getter
@SuperBuilder
public class ObjectNotFoundExceptionDetails extends ExceptionDetails {
}


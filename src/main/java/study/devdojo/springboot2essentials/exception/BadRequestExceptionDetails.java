package study.devdojo.springboot2essentials.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Classe BadRequestExceptionDetails.
 * Esta classe representa os detalhes específicos de uma exceção do tipo BadRequestException.
 * Estende a classe ExceptionDetails para adicionar informações adicionais específicas para esse tipo de exceção.
 * Ela herda campos genéricos da classe ExceptionDetails e pode incluir campos adicionais conforme necessário.
 * Serve para encapsular informações detalhadas de uma BadRequestException, como título, código de status HTTP,
 * detalhes adicionais, mensagem para desenvolvedores e timestamp da ocorrência da exceção.
 */
@Getter
@SuperBuilder
public class BadRequestExceptionDetails extends ExceptionDetails {
}

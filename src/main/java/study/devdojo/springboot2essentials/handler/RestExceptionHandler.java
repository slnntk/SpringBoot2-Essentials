package study.devdojo.springboot2essentials.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import study.devdojo.springboot2essentials.exception.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por lidar com exceções lançadas pela aplicação durante requisições REST.
 * Utiliza a anotação @ControllerAdvice para centralizar o tratamento de exceções.
 * Este controlador aconselha todos os controladores (Controllers) dentro da aplicação,
 * centralizando o tratamento de exceções, permitindo uma abordagem mais global e organizada.
 */
@ControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Método handleBadRequestException.
     * Este método trata exceções do tipo BadRequestException, gerando uma resposta com detalhes específicos
     * da exceção BadRequestException. Retorna um ResponseEntity contendo detalhes da exceção, como timestamp,
     * status HTTP, título, detalhes, mensagem do desenvolvedor, entre outros, e o código de status HTTP 400 (Bad Request).
     *
     * @param bre Exceção BadRequestException lançada na aplicação.
     * @return ResponseEntity contendo os detalhes da exceção BadRequestException e código de status HTTP 400.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException bre) {
        // Lógica para construir e retornar um ResponseEntity com detalhes específicos da BadRequestException
        // Timestamp, status HTTP, título, detalhes, mensagem do desenvolvedor são encapsulados em um objeto BadRequestExceptionDetails
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Check the Documentation")
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }


    /**
     * Método handleObjectNotFoundException.
     * Este método trata exceções do tipo ObjectNotFoundException, gerando uma resposta com detalhes específicos
     * da exceção ObjectNotFoundException. Retorna um ResponseEntity contendo detalhes da exceção, como timestamp,
     * status HTTP, título, detalhes, mensagem do desenvolvedor, entre outros, e o código de status HTTP 404 (Not Found).
     *
     * @param objectNotFoundException Exceção ObjectNotFoundException lançada na aplicação.
     * @return ResponseEntity contendo os detalhes da exceção ObjectNotFoundException e código de status HTTP 404.
     */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ObjectNotFoundExceptionDetails> handleObjectNotFoundException(ObjectNotFoundException objectNotFoundException) {
        // Lógica para construir e retornar um ResponseEntity com detalhes específicos da ObjectNotFoundException
        // Timestamp, status HTTP, título, detalhes, mensagem do desenvolvedor são encapsulados em um objeto ObjectNotFoundExceptionDetails
        return new ResponseEntity<>(
                ObjectNotFoundExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Object Not Found Exception")
                        .details(objectNotFoundException.getMessage())
                        .developerMessage(objectNotFoundException.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }


    /**
     * Método handleMethodArgumentNotValid.
     * Este método trata exceções do tipo MethodArgumentNotValidException, gerando uma resposta com detalhes
     * sobre campos inválidos nos dados da requisição. Retorna um ResponseEntity contendo detalhes sobre os campos
     * inválidos, como timestamp, status HTTP, título, detalhes, mensagem do desenvolvedor, entre outros, e o
     * código de status HTTP 400 (Bad Request).
     *
     * @param exception Exceção MethodArgumentNotValidException lançada na aplicação.
     * @param headers   Cabeçalhos da requisição.
     * @param status    Status HTTP.
     * @param request   Requisição.
     * @return ResponseEntity contendo detalhes sobre campos inválidos e código de status HTTP 400.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Invalid Fields")
                        .details("Check the field(s) error")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Método handleExceptionInternal.
     * Este método trata exceções internas, gerando uma resposta com detalhes gerais da exceção interna. Retorna
     * um ResponseEntity contendo detalhes gerais da exceção, como timestamp, status HTTP, título, detalhes, mensagem
     * do desenvolvedor, entre outros, e o código de status HTTP apropriado.
     *
     * @param ex       Exceção interna gerada pelo sistema.
     * @param body     Corpo da resposta.
     * @param headers  Cabeçalhos da requisição.
     * @param status   Status HTTP.
     * @param request  Requisição.
     * @return ResponseEntity contendo detalhes gerais da exceção interna e código de status HTTP apropriado.
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title(ex.getCause().getMessage())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }

}

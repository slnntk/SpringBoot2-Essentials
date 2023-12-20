package study.devdojo.springboot2essentials.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
public class RestExceptionHandler {

    /**
     * Método para lidar com a exceção BadRequestException.
     *
     * @param badRequestException Exceção BadRequestException lançada na aplicação.
     * @return ResponseEntity contendo os detalhes da exceção e o código de status HTTP BAD_REQUEST.
     * Este método trata exceções do tipo BadRequestException, retornando um ResponseEntity com os detalhes
     * da exceção, como título, detalhes, mensagem do desenvolvedor, entre outros, e o código de status HTTP 400.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Check the Documentation")
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Método para lidar com a exceção MethodArgumentNotValidException.
     *
     * @param methodArgumentNotValidException Exceção MethodArgumentNotValidException lançada na aplicação.
     * @return ResponseEntity contendo os detalhes da exceção e o código de status HTTP BAD_REQUEST.
     * Este método trata exceções do tipo MethodArgumentNotValidException, retornando um ResponseEntity com os detalhes
     * da exceção, como título, detalhes, campos inválidos, mensagem do desenvolvedor, entre outros,
     * e o código de status HTTP 400.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Invalid Fields")
                        .details("Check the field(s) error")
                        .developerMessage(methodArgumentNotValidException.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Método para lidar com a exceção ObjectNotFoundException.
     *
     * @param objectNotFoundException Exceção ObjectNotFoundException lançada na aplicação.
     * @return ResponseEntity contendo os detalhes da exceção e o código de status HTTP NOT_FOUND.
     * Este método trata exceções do tipo ObjectNotFoundException, retornando um ResponseEntity com os detalhes
     * da exceção, como título, detalhes, mensagem do desenvolvedor, entre outros, e o código de status HTTP 404.
     */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ObjectNotFoundExceptionDetails> handlerObjectNotFoundException(ObjectNotFoundException objectNotFoundException) {
        return new ResponseEntity<>(
                ObjectNotFoundExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Object Not Found Exception")
                        .details(objectNotFoundException.getMessage())
                        .developerMessage(objectNotFoundException.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }
}

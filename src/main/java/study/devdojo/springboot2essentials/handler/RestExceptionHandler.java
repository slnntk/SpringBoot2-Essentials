package study.devdojo.springboot2essentials.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import study.devdojo.springboot2essentials.exception.BadRequestException;
import study.devdojo.springboot2essentials.exception.ObjectNotFoundException;
import study.devdojo.springboot2essentials.exception.StandardError;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Classe responsável por lidar com exceções lançadas pela aplicação durante requisições REST.
 * Utiliza a anotação @ControllerAdvice para centralizar o tratamento de exceções.
 */
@ControllerAdvice
public class RestExceptionHandler {

    /**
     * Método para lidar com a exceção BadRequestException.
     * @param badRequestException Exceção BadRequestException lançada na aplicação.
     * @return ResponseEntity contendo os detalhes da exceção e o código de status HTTP BAD_REQUEST.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> handlerBadRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<StandardError>(
                StandardError.builder()
                        .title("Bad Request Exception, Check the Documentation")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFoundException(ObjectNotFoundException objectNotFoundException) {
        return new ResponseEntity<StandardError>(
                StandardError.builder()
                        .title("Object Not Found Exception, Check the Request")
                        .status(HttpStatus.NOT_FOUND.value())
                        .details(objectNotFoundException.getMessage())
                        .developerMessage(objectNotFoundException.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }
}

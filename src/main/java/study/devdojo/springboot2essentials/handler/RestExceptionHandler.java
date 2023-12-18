package study.devdojo.springboot2essentials.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import study.devdojo.springboot2essentials.exception.BadRequestException;
import study.devdojo.springboot2essentials.exception.BadRequestExceptionDetails;

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
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<BadRequestExceptionDetails>(
                BadRequestExceptionDetails.builder()
                        .title("Bad Request Exception, Check the Documentation")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }
}

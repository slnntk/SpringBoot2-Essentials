package study.devdojo.springboot2essentials.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import study.devdojo.springboot2essentials.util.LoggerUtil;

/**
 * Classe responsável por realizar logging de métodos no controlador (Controller).
 */
@Aspect
@Component
@Log4j2
public class ControllerLoggingAspect {

    /**
     * Método executado antes de métodos no AnimeController.
     * Realiza logging das informações sobre o método, classe e thread.
     * @param joinPoint Ponto de junção da execução do método.
     */
    @Before("execution(* study.devdojo.springboot2essentials.controller.AnimeController.*(..))")
    public void beforeControllerMethods(JoinPoint joinPoint) {
        // Obtém o nome do método, classe e nome da thread em execução.
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String threadName = Thread.currentThread().getName();

        // Realiza o log das informações utilizando a classe LoggerUtil.
        LoggerUtil.logInfo(className.getClass(), methodName, threadName);
    }
}

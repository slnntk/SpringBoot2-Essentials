package study.devdojo.springboot2essentials.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import study.devdojo.springboot2essentials.util.LoggerUtil;

@Aspect
@Component
@Log4j2
public class ControllerLoggingAspect {

    @Before("execution(* study.devdojo.springboot2essentials.controller.AnimeController.*(..))")
    public void beforeControllerMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String threadName = Thread.currentThread().getName();

        LoggerUtil.logInfo(className.getClass(), methodName, threadName);
    }
}

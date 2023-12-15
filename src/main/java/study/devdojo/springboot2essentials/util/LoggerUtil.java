package study.devdojo.springboot2essentials.util;

import lombok.extern.log4j.Log4j2;
import java.time.LocalDateTime;

@Log4j2
public class LoggerUtil {

    public static String generateLogMessage(Class<?> clazz, String method, String thread) {
        return DateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()) + "  " +
                "Class: " + clazz.getSimpleName() + "  " +
                "Method: " + method + "  " +
                "Thread: " + thread + "  ";
    }

    public static void logInfo(Class<?> clazz, String method, String thread) {
        log.info(generateLogMessage(clazz, method, thread));
    }

}

package study.devdojo.springboot2essentials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableAutoConfiguration
//@ComponentScan()
//@Configuration

// Essas 3 annotations são as principais para inicializar um projeto spring
// substituídas pela @SpringBootApplication

@SpringBootApplication
public class ApplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }

}

package study.devdojo.springboot2essentials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal responsável por iniciar a aplicação Spring Boot.
 * Utiliza a anotação @SpringBootApplication para configurar automaticamente a aplicação.
 * É o ponto de entrada da aplicação, iniciando o contexto do Spring.
 */
@SpringBootApplication
public class ApplicationStart {

    /**
     * Método main da aplicação Spring Boot.
     * Inicializa a aplicação Spring utilizando SpringApplication.run().
     *
     * @param args Argumentos passados via linha de comando durante a inicialização.
     */
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }

}

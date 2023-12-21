package study.devdojo.springboot2essentials.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Classe de configuração para personalizar os argumentos dos métodos manipuladores (handlers) em um ambiente Spring MVC.
 * Implementa a interface WebMvcConfigurer, permitindo adicionar argument resolvers personalizados.
 */
@Configuration
public class DeveloperWebMvcConfigurer implements WebMvcConfigurer {

    /**
     * Método responsável por adicionar argument resolvers personalizados aos métodos manipuladores.
     * Adiciona um PageableHandlerMethodArgumentResolver configurado com uma página padrão de fallback.
     *
     * @param resolvers Lista de argument resolvers atualmente registrados.
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // Cria um PageableHandlerMethodArgumentResolver para lidar com a resolução de objetos Pageable.
        PageableHandlerMethodArgumentResolver pageableHandler =  new PageableHandlerMethodArgumentResolver();

        // Define uma página padrão de fallback (0, 5) caso não haja parâmetros de paginação na solicitação.
        pageableHandler.setFallbackPageable(PageRequest.of(0, 5));

        // Adiciona o PageableHandlerMethodArgumentResolver à lista de argument resolvers.
        resolvers.add(pageableHandler);
    }
}

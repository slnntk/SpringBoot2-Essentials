package study.devdojo.springboot2essentials.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.repository.AnimeRepository;
import study.devdojo.springboot2essentials.util.AnimeCreator;
import study.devdojo.springboot2essentials.wrapper.PageableResponse;

/**
 * Esta classe de teste de integração é responsável por testar o controlador AnimeController.
 * Utiliza a annotation @SpringBootTest para configurar o ambiente de teste.
 * A opção 'webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT' configura o ambiente de teste
 * para iniciar a aplicação Spring Boot em uma porta aleatória durante os testes de integração.
 * Isso é útil para evitar possíveis conflitos de porta com outros serviços em execução durante os testes.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class AnimeControllerT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private AnimeRepository animeRepository;

    /**
     * Teste para verificar se o método list() em AnimeController retorna uma lista de animes dentro de um objeto de página quando bem-sucedido.
     */
    @Test
    @DisplayName("list returns list of anime inside page object when successful")
    void list_ReturnsListOfAnimesInsidePageObject_WhenSuccessful(){
        // Salvando um anime para teste
        Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());

        // Obtendo o nome esperado do anime salvo
        String expectedName = savedAnime.getName();

        // Chamando o endpoint '/animes' usando o método exchange do TestRestTemplate para obter uma lista paginada de animes
        PageableResponse<Anime> animePage = testRestTemplate.exchange("/animes", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Anime>>() {
                }).getBody();

        // Verificando se a resposta não é nula
        Assertions.assertThat(animePage).isNotNull();

        // Verificando se a lista de animes na página não está vazia e tem tamanho 1
        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        // Verificando se o nome do primeiro anime na lista corresponde ao nome esperado
        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }
}


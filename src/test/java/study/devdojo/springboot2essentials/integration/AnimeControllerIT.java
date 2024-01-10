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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.repository.AnimeRepository;
import study.devdojo.springboot2essentials.requests.AnimePostRequestBody;
import study.devdojo.springboot2essentials.util.AnimeCreator;
import study.devdojo.springboot2essentials.util.AnimePostRequestBodyCreator;
import study.devdojo.springboot2essentials.wrapper.PageableResponse;

import java.util.List;

/**
 * Classe de testes de integração para o controlador AnimeController.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class AnimeControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private AnimeRepository animeRepository;

    /**
     * Teste para verificar se a listagem de animes retorna uma lista dentro de um objeto de página quando bem-sucedida.
     */
    @Test
    @DisplayName("list returns list of anime inside page object when successful")
    void list_ReturnsListOfAnimesInsidePageObject_WhenSuccessful() {
        // Cria e salva um Anime para uso no teste
        Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());
        String expectedName = savedAnime.getName();

        // Realiza uma requisição GET para '/animes' e obtém um PageableResponse<Anime>
        PageableResponse<Anime> animePage = testRestTemplate.exchange("/animes", HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponse<Anime>>() {
        }).getBody();

        // Assertions para verificar se o animePage não é nulo, se não está vazio e se tem tamanho 1
        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);

        // Verifica se o nome do primeiro Anime na lista corresponde ao nome esperado
        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listAll returns list of anime when successful")
    void listAll_ReturnsListOfAnimes_WhenSuccessful() {
        // Salva um novo anime no repositório para uso nos testes
        Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());

        // Obtém o nome do anime salvo para comparação posterior
        String expectedName = savedAnime.getName();

        // Faz uma requisição GET para o endpoint "/animes/all" usando o TestRestTemplate
        List<Anime> animes = testRestTemplate.exchange("/animes/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<Anime>>() {
        }).getBody();

        animes.forEach(System.out::println);



        // Verifica se a lista de animes obtida não é nula, não está vazia e tem tamanho 1
        Assertions.assertThat(animes).isNotNull().isNotEmpty().hasSize(1);

        // Verifica se o nome do primeiro anime na lista corresponde ao nome esperado
        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns anime when successful")
    void findById_ReturnsAnime_WhenSuccessful() {
        // Salva um novo anime no repositório para uso nos testes
        Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());

        // Obtém o ID do anime salvo para uso na busca posterior
        Long expectedId = savedAnime.getId();

        // Faz uma requisição GET para o endpoint "/animes/{id}" usando o TestRestTemplate,
        // passando o ID do anime salvo para buscar um anime específico
        Anime anime = testRestTemplate.getForObject("/animes/{id}", Anime.class, expectedId);

        // Verifica se o anime retornado não é nulo
        Assertions.assertThat(anime).isNotNull();

        // Verifica se o ID do anime retornado é igual ao ID esperado
        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a list of anime when successful")
    void findByName_ReturnsListOfAnime_WhenSuccessful() {
        // Salva um novo anime no repositório para uso nos testes
        Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());

        // Obtém o nome do anime salvo para uso na busca posterior
        String expectedName = savedAnime.getName();

        // Cria a URL com o nome do anime a ser usado na busca
        String url = String.format("/animes/find?name=%s", expectedName);

        // Faz uma requisição GET para o endpoint "/animes/find" usando o TestRestTemplate,
        // passando o parâmetro 'name' com o nome do anime para buscar uma lista de animes pelo nome
        List<Anime> animes = testRestTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Anime>>() {
        }).getBody();

        // Verifica se a lista de animes retornada não é nula, não está vazia e tem tamanho 1
        Assertions.assertThat(animes).isNotNull().isNotEmpty().hasSize(1);

        // Verifica se o nome do primeiro anime na lista corresponde ao nome esperado
        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of anime when anime is not found")
    void findByName_ReturnsEmptyListOfAnime_WhenAnimeIsNotFound() {
        // Faz uma requisição GET para o endpoint "/animes/find?name=dbz" usando o TestRestTemplate,
        // passando um nome de anime que não existe no repositório para verificar se retorna uma lista vazia
        List<Anime> animes = testRestTemplate.exchange("/animes/find?name=dbz", HttpMethod.GET, null, new ParameterizedTypeReference<List<Anime>>() {
        }).getBody();

        // Verifica se a lista de animes retornada não é nula e está vazia
        Assertions.assertThat(animes).isNotNull().isEmpty();
    }


    @Test
    @DisplayName("save returns anime when successful")
    void save_ReturnsAnime_WhenSuccessful() {
        // Cria um corpo de requisição para adicionar um novo anime
        AnimePostRequestBody animePostRequestBody = AnimePostRequestBodyCreator.createAnimePostRequestBody();

        // Envia uma requisição POST para o endpoint "/animes" usando o TestRestTemplate,
        // enviando o corpo da requisição para criar um novo anime
        ResponseEntity<Anime> animeResponseEntity = testRestTemplate.postForEntity("/animes", animePostRequestBody, Anime.class);

        // Verifica se a resposta da requisição não é nula
        Assertions.assertThat(animeResponseEntity).isNotNull();
        // Verifica se o status da resposta é HttpStatus.CREATED (201 - Criado)
        Assertions.assertThat(animeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        // Verifica se o corpo da resposta (o anime criado) não é nulo
        Assertions.assertThat(animeResponseEntity.getBody()).isNotNull();
        // Verifica se o ID do anime criado não é nulo
        Assertions.assertThat(animeResponseEntity.getBody().getId()).isNotNull();
    }


    @Test
    @DisplayName("replace updates anime when successful")
    void replace_UpdatesAnime_WhenSuccessful() {
        // Salva um novo anime no repositório para uso nos testes
        Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());

        // Atualiza o nome do anime salvo para um novo nome
        savedAnime.setName("new name");

        // Envia uma requisição PUT para o endpoint "/animes" usando o TestRestTemplate,
        // com o corpo atualizado do anime salvo para substituir o anime existente
        ResponseEntity<Void> animeResponseEntity = testRestTemplate.exchange("/animes", HttpMethod.PUT, new HttpEntity<>(savedAnime), Void.class);

        // Verifica se a resposta da requisição não é nula
        Assertions.assertThat(animeResponseEntity).isNotNull();

        // Verifica se o status da resposta é HttpStatus.NO_CONTENT (204 - Sem conteúdo)
        Assertions.assertThat(animeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}

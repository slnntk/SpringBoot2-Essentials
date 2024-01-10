package study.devdojo.springboot2essentials.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.requests.AnimePostRequestBody;
import study.devdojo.springboot2essentials.requests.AnimePutRequestBody;
import study.devdojo.springboot2essentials.service.AnimeService;
import study.devdojo.springboot2essentials.util.AnimeCreator;
import study.devdojo.springboot2essentials.util.AnimePostRequestBodyCreator;
import study.devdojo.springboot2essentials.util.AnimePutRequestBodyCreator;
import study.devdojo.springboot2essentials.util.DateUtil;

import java.util.Collections;
import java.util.List;

/**
 * Classe de teste para AnimeController.
 * Utiliza @ExtendWith em conjunto com SpringExtension para integrar o Spring com JUnit,
 * permitindo testes mais isolados e independentes sem iniciar o contexto completo da aplicação.
 */


@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    // Utiliza-se quando a classe que você está testando será instanciada dentro do teste, testando a classe em si.
    @InjectMocks
    private AnimeController animeController; // Classe sendo testada

    // Utiliza-se para testar classes utilizadas dentro de AnimeController, como serviços (ex: AnimeService)
    @Mock
    private AnimeService animeServiceMock; // Mock do serviço AnimeService

    // Utiliza-se para testar classes utilizadas dentro de AnimeController, como serviços (ex: AnimeService)
    @Mock
    private DateUtil dateUtil; // Mock da classe utilitária DateUtil

    /**
     * Método anotado com @BeforeEach, que é executado antes de cada teste.
     * Ele configura um cenário prévio para os testes, preparando o ambiente
     * com uma página de animes simulada contendo um único anime válido.
     * Configura também o comportamento do mock do serviço (animeService)
     * para o método listAll, de modo que qualquer chamada para animeService.listAll(),
     * independentemente do argumento passado, retornará a animePage criada anteriormente.
     */

    @BeforeEach
    void setUp() {
        configureAnimeServiceMockBehavior();
    }

    /**
     * Método para configurar o comportamento dos mocks para os métodos do AnimeService.
     * Define o comportamento dos mocks para as chamadas de métodos do serviço usados nos testes.
     */
    private void configureAnimeServiceMockBehavior() {
        // Cria uma página de animes simulada com um único anime válido usando o AnimeCreator.
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));

        // Configura o comportamento dos mocks para os métodos do AnimeService.
        BDDMockito.when(animeServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(animePage);

        BDDMockito.when(animeServiceMock.listAllNonPageable())
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeServiceMock.save(ArgumentMatchers.any(AnimePostRequestBody.class)))
                .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.doNothing().when(animeServiceMock).replace(ArgumentMatchers.any(AnimePutRequestBody.class));

        BDDMockito.doNothing().when(animeServiceMock).delete(ArgumentMatchers.anyLong());
    }


    /**
     * Teste para verificar se o método list() em AnimeController retorna uma lista de animes válidos.
     */
    @Test
    @DisplayName("list returns list of anime inside page object when successful")
    void list_ReturnsListOfAnimesInsidePageObject_WhenSuccessful() {
        String expectedName = AnimeCreator.createValidAnime().getName();

        Page<Anime> animePage = animeController.list(null).getBody();

        Assertions.assertThat(animePage).isNotNull();

        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    /**
     * Teste para verificar se o método listAll() em AnimeController retorna uma lista de animes com sucesso.
     */
    @Test
    @DisplayName("listAll returns list of anime when successful")
    void listAll_ReturnsListOfAnimes_WhenSuccessful() {
        String expectedName = AnimeCreator.createValidAnime().getName();

        List<Anime> animes = animeController.listAll().getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    /**
     * Teste para verificar se o método findById() em AnimeController retorna um anime com sucesso.
     */
    @Test
    @DisplayName("findById returns anime when successful")
    void findById_ReturnsAnime_WhenSuccessful() {
        Long expectedId = AnimeCreator.createValidAnime().getId();

        Anime anime = animeController.findById(1).getBody();

        Assertions.assertThat(anime).isNotNull();

        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
    }

    /**
     * Teste para verificar se o método findByName() em AnimeController retorna uma lista de animes com sucesso
     * ao buscar por um nome específico.
     */
    @Test
    @DisplayName("findByName returns a list of anime when successful")
    void findByName_ReturnsListOfAnime_WhenSuccessful() {
        String expectedName = AnimeCreator.createValidAnime().getName();

        List<Anime> animes = animeController.findByName("anime").getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    /**
     * Testa se o método findByName() em AnimeController retorna uma lista vazia de animes
     * quando nenhum anime é encontrado com o nome especificado.
     */
    @Test
    @DisplayName("findByName returns an empty list of anime when anime is not found")
    void findByName_ReturnsEmptyListOfAnime_WhenAnimeIsNotFound() {
        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Anime> animes = animeController.findByName("anime").getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isEmpty();

    }

    /**
     * Testa se o método save() em AnimeController retorna um anime quando a operação é bem-sucedida.
     */
    @Test
    @DisplayName("save returns anime when successful")
    void save_ReturnsAnime_WhenSuccessful() {

        Anime anime = animeController.save(AnimePostRequestBodyCreator.createAnimePostRequestBody()).getBody();

        Assertions.assertThat(anime).isNotNull().isEqualTo(AnimeCreator.createValidAnime());

    }

    /**
     * Testa se o método replace() em AnimeController atualiza um anime com sucesso.
     */
    @Test
    @DisplayName("replace updates anime when successful")
    void replace_UpdatesAnime_WhenSuccessful() {

        Assertions.assertThatCode(() -> animeController.replace(AnimePutRequestBodyCreator.createAnimePutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = animeController.replace(AnimePutRequestBodyCreator.createAnimePutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    /**
     * Testa se o método delete() em AnimeController remove um anime com sucesso.
     */
    @Test
    @DisplayName("delete removes anime when successful")
    void delete_RemovesAnime_WhenSuccessful() {

        Assertions.assertThatCode(() -> animeController.delete(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = animeController.delete(1);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}

/**
 * Ao executar testes unitários, uma opção comum é usar a anotação @SpringBootTest. No entanto, essa abordagem
 * pode gerar um problema: o contexto do Spring é inicializado, o que tenta iniciar a aplicação e, caso o banco
 * de dados não esteja conectado ou configurado, pode resultar em erros no ApplicationContext.
 * <p>
 * Para evitar esse comportamento e realizar testes unitários mais específicos, optamos por utilizar a anotação
 *
 * @ExtendWith em conjunto com SpringExtension. Isso nos permite integrar o Spring com o JUnit sem iniciar
 * o contexto completo da aplicação, garantindo que os testes sejam mais isolados e independentes, sem a necessidade
 * de uma conexão com o banco de dados ou outros recursos externos.
 */

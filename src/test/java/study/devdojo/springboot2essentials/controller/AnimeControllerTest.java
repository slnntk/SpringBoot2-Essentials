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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.service.AnimeService;
import study.devdojo.springboot2essentials.util.AnimeCreator;
import study.devdojo.springboot2essentials.util.DateUtil;

import java.util.List;


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

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks
    // Utiliza-se quando a classe que você está testando será instanciada dentro do teste, testando a classe em si.
    private AnimeController animeController;
    @Mock // Utiliza-se para testar classes utilizadas dentro de AnimeController, como serviços (ex: AnimeService)
    private AnimeService animeService; // Vamos fazer um mock do comportamento da classe AnimeService.
    @Mock
    private DateUtil dateUtil;

    /**
     * Método anotado com @BeforeEach, que é executado antes de cada teste.
     * Ele configura um cenário prévio para os testes, preparando o ambiente
     * com uma página de animes simulada contendo um único anime válido.
     * Configura também o comportamento do mock do serviço (animeServiceMock)
     * para o método listAll, de modo que qualquer chamada para animeService.listAll(),
     * independentemente do argumento passado, retornará a animePage criada anteriormente.
     */
    @BeforeEach
    void setUp() {
        // Cria uma página de animes simulada com um único anime válido usando o AnimeCreator.
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));

        // Configura o comportamento do mock do serviço (animeServiceMock) para o método listAll,
        // onde qualquer chamada para animeService.listAll(), independentemente do argumento passado,
        // irá retornar a animePage criada anteriormente.
        BDDMockito.when(animeService.listAll(ArgumentMatchers.any()))
                .thenReturn(animePage);
    }

    @Test
    @DisplayName("List returns list of animes inside page object when successful")
    void list_ReturnsListOfAnimesInsidePageObject_WhenSuccessful() {
        String expectedName = AnimeCreator.createValidAnime().getName();

        Page<Anime> animePage = animeController.list(null).getBody();

        Assertions.assertThat(animePage)
                .isNotNull();

        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage
                        .toList().get(0)
                        .getName())
                .isEqualTo(expectedName);
    }
}

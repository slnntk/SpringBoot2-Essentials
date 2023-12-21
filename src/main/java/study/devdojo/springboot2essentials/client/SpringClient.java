package study.devdojo.springboot2essentials.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import study.devdojo.springboot2essentials.domain.Anime;

import java.util.Arrays;
import java.util.List;

/**
 * Classe SpringClient utilizando RestTemplate para realizar chamadas a endpoints REST.
 */
@Log4j2
public class SpringClient {

    private static final String BASE_URL = "http://localhost:8080/animes";

    public static void main(String[] args) {
        // Chamada para obter um ResponseEntity com um Anime específico pelo ID.
        ResponseEntity<Anime> entity = getAnimeById(2);
        log.info(entity);

        // Chamada para obter um objeto do tipo Anime pelo ID.
        Anime object = getAnimeObjectById(2);
        log.info(object);

        // Chamada para obter um array de Animes.
        Anime[] animesArray = getAllAnimesAsArray();
        log.info(Arrays.toString(animesArray));

        // Chamada para obter uma lista de Animes.
        ResponseEntity<List<Anime>> animesList = getAllAnimesAsList();
        log.info(animesList);
    }

    /**
     * Obtém um ResponseEntity contendo um Anime específico pelo ID.
     *
     * @param id ID do Anime a ser obtido.
     * @return ResponseEntity contendo o Anime e detalhes da resposta.
     */
    private static ResponseEntity<Anime> getAnimeById(long id) {
        String url = BASE_URL + "/{id}";
        return new RestTemplate().getForEntity(url, Anime.class, id);
    }

    /**
     * Obtém um objeto do tipo Anime pelo ID.
     *
     * @param id ID do Anime a ser obtido.
     * @return Objeto do tipo Anime.
     */
    private static Anime getAnimeObjectById(long id) {
        String url = BASE_URL + "/{id}";
        return new RestTemplate().getForObject(url, Anime.class, id);
    }

    /**
     * Obtém um array de Animes.
     *
     * @return Array de Animes.
     */
    private static Anime[] getAllAnimesAsArray() {
        String url = BASE_URL + "/all";
        return new RestTemplate().getForObject(url, Anime[].class);
    }

    /**
     * Obtém uma lista de Animes.
     *
     * @return ResponseEntity contendo uma lista de Animes e detalhes da resposta.
     */
    private static ResponseEntity<List<Anime>> getAllAnimesAsList() {
        String url = BASE_URL + "/all";
        return new RestTemplate().exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Anime>>() {
                }
        );
    }
}

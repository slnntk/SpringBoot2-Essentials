package study.devdojo.springboot2essentials.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import study.devdojo.springboot2essentials.domain.Anime;

import java.util.Arrays;
import java.util.List;

/**
 * Classe SpringClient usando RestTemplate para fazer chamadas a endpoints REST.
 */

@Log4j2
public class SpringClient {

    /**
     * Um ResponseEntity<Anime> encapsula um objeto Anime juntamente com metadados da resposta HTTP, como status e cabeçalhos.
     * <p>
     * Enquanto isso, um objeto Anime representa apenas os dados do domínio, sem informações extras da resposta HTTP.
     * <p>
     * O uso de ResponseEntity é útil quando é necessário acessar informações além do objeto Anime, como status da resposta.
     * Já o objeto Anime é usado apenas para representar os dados principais da chamada.
     */

    private static final String BASE_URL = "http://localhost:8080/animes";

    public static void main(String[] args) {
        // Chamada para obter um ResponseEntity contendo um Anime
        ResponseEntity<Anime> responseEntity = getAnimeById(2);
        log.info("Response ResponseEntity: " + responseEntity);

        // Chamada para obter um objeto Anime específico pelo ID.
        Anime animeObject = getAnimeObjectById(2);
        log.info("Anime Object: " + animeObject);

        // Chamada para obter um array de Animes.
        Anime[] animeArray = getAllAnimesAsArray();
        log.info("Array of Animes: " + Arrays.toString(animeArray));

        // Chamada para obter uma lista de Animes como ResponseEntity.
        ResponseEntity<List<Anime>> responseListAnimes = getAllAnimesAsList();
        log.info("Response ResponseEntity<List<Anime>>: " + responseListAnimes);

        // Chamada para adicionar um novo Anime.
        Anime newAnime = Anime.builder().name("Samurai Champloo").build();
        ResponseEntity<Anime> savedAnimeResponse = addNewAnime(newAnime);
        log.info("Saved Anime: {}", savedAnimeResponse);

        Anime updatedAnime = Anime.builder().id(2L).name("New Name").build();
        ResponseEntity<Void> updateResponse = updateAnime(updatedAnime);
        log.info("Update Response: " + updateResponse);

        // Chamada para excluir um Anime pelo ID
        ResponseEntity<Void> deleteResponse = deleteAnime(2L);
        log.info("Delete Response: " + deleteResponse);
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
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
     * @return O objeto do tipo Anime.
     */
    private static Anime getAnimeObjectById(long id) {
        String url = BASE_URL + "/{id}";
        return new RestTemplate().getForObject(url, Anime.class, id);
    }

    /**
     * Obtém um array de Animes.
     *
     * @return Um array de Animes.
     */
    private static Anime[] getAllAnimesAsArray() {
        String url = BASE_URL + "/all";
        return new RestTemplate().getForObject(url, Anime[].class);
    }

    /**
     * Obtém uma lista de Animes como ResponseEntity.
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

    /**
     * Adiciona um novo Anime e retorna a resposta do servidor.
     *
     * @param anime Novo Anime a ser adicionado.
     * @return O ResponseEntity contendo o Anime adicionado.
     */
    private static ResponseEntity<Anime> addNewAnime(Anime anime) {
        String url = BASE_URL + "/";
        HttpHeaders headers = createJsonHeader();
        HttpEntity<Anime> request = new HttpEntity<>(anime, headers);

        return new RestTemplate().exchange(url,
                HttpMethod.POST, request,
                Anime.class);
    }

    /**
     * Atualiza um Anime existente na API.
     *
     * @param anime Objeto Anime com os dados atualizados.
     * @return ResponseEntity indicando o resultado da atualização.
     */
    private static ResponseEntity<Void> updateAnime(Anime anime) {
        String url = BASE_URL + "/{id}";
        HttpHeaders headers = createJsonHeader();
        HttpEntity<Anime> request = new HttpEntity<>(anime, headers);

        return new RestTemplate().exchange(url,
                HttpMethod.PUT,
                request,
                Void.class,
                anime.getId());
    }

    /**
     * Exclui um Anime da API pelo ID.
     *
     * @param id ID do Anime a ser excluído.
     * @return ResponseEntity indicando o resultado da exclusão.
     */
    private static ResponseEntity<Void> deleteAnime(Long id) {
        String url = BASE_URL + "/{id}";
        return new RestTemplate().exchange(url,
                HttpMethod.DELETE,
                null,
                Void.class,
                id);
    }

}

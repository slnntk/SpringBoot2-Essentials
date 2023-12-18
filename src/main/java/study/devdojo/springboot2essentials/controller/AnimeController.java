package study.devdojo.springboot2essentials.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.requests.AnimePostRequestBody;
import study.devdojo.springboot2essentials.requests.AnimePutRequestBody;
import study.devdojo.springboot2essentials.service.AnimeService;
import study.devdojo.springboot2essentials.util.DateUtil;

import java.util.List;

/**
 * Controlador REST responsável por gerenciar requisições relacionadas aos Animes.
 */
@RestController
@RequestMapping("animes") // Rota base para endpoints relacionados aos Animes.
@Log4j2
@RequiredArgsConstructor // Gera um construtor com todos os argumentos finals.
public class AnimeController {

    private final DateUtil dateUtil; // Utilitário para obtenção de data/hora.
    private final AnimeService animeService; // Serviço responsável pela lógica de negócio dos Animes.

    /**
     * Endpoint para listar todos os Animes.
     * @return ResponseEntity contendo a lista de Animes e o código de status HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<Anime>> list() {
        return ResponseEntity.ok(animeService.listAll());
    }

    /**
     * Endpoint para buscar um Anime pelo ID.
     * @param id ID do Anime a ser buscado.
     * @return ResponseEntity contendo o Anime encontrado e o código de status HTTP OK.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
    }

    /**
     * Endpoint para criar um novo Anime.
     * @param animePostRequestBody Corpo da requisição com os dados do Anime a ser criado.
     * @return ResponseEntity contendo o Anime criado e o código de status HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody AnimePostRequestBody animePostRequestBody) {
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    /**
     * Endpoint para deletar um Anime pelo ID.
     * @param id ID do Anime a ser deletado.
     * @return ResponseEntity com código de status HTTP NO_CONTENT (sem conteúdo).
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint para atualizar um Anime.
     * @param animePutRequestBody Corpo da requisição com os dados atualizados do Anime.
     * @return ResponseEntity com código de status HTTP NO_CONTENT (sem conteúdo).
     */
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

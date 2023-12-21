package study.devdojo.springboot2essentials.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.requests.AnimePostRequestBody;
import study.devdojo.springboot2essentials.requests.AnimePutRequestBody;
import study.devdojo.springboot2essentials.service.AnimeService;
import study.devdojo.springboot2essentials.util.DateUtil;

import javax.validation.Valid;
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
     * Endpoint para listar os Animes com paginação.
     * Utiliza o parâmetro 'pageable' para permitir a paginação dos resultados.
     *
     * @param pageable Objeto que encapsula informações de paginação como número da página, tamanho da página, etc.
     * @return ResponseEntity contendo a página de Animes e o código de status HTTP OK (200 - OK).
     */
    @GetMapping
    public ResponseEntity<Page<Anime>> list(Pageable pageable) {
        return ResponseEntity.ok(animeService.listAll(pageable));
    }


    /**
     * Endpoint para buscar um Anime pelo ID.
     *
     * @param id ID do Anime a ser buscado.
     * @return ResponseEntity contendo o Anime encontrado e o código de status HTTP OK.
     * (200 - OK)
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
    }

    /**
     * Endpoint para buscar Animes pelo nome.
     *
     * @param name Nome do Anime a ser buscado.
     * @return ResponseEntity contendo a lista de Animes encontrados e o código de status HTTP OK.
     * (200 - OK)
     */
    @GetMapping(path = "/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam(required = true) String name) {
        return ResponseEntity.ok(animeService.findByName(name));
    }

    /**
     * Endpoint para criar um novo Anime.
     *
     * @param animePostRequestBody Corpo da requisição com os dados do Anime a ser criado.
     *                             O parâmetro é validado usando a anotação @Valid, que ativa a validação
     *                             das constraints definidas na classe AnimePostRequestBody, como por exemplo
     *                             a validação de campos obrigatórios, tamanho, formato, entre outros.
     * @return ResponseEntity contendo o Anime criado e o código de status HTTP CREATED (201 - CREATED).
     */
    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody) {
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }


    /**
     * Endpoint para deletar um Anime pelo ID.
     *
     * @param id ID do Anime a ser deletado.
     * @return ResponseEntity com código de status HTTP NO_CONTENT (sem conteúdo).
     * (204 - NO_CONTENT)
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint para atualizar um Anime.
     *
     * @param animePutRequestBody Corpo da requisição com os dados atualizados do Anime.
     * @return ResponseEntity com código de status HTTP NO_CONTENT (sem conteúdo).
     * (204 - NO_CONTENT)
     */
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

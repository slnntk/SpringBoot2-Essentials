package study.devdojo.springboot2essentials.controller;

import com.fasterxml.jackson.core.JsonParser;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.service.AnimeService;
import study.devdojo.springboot2essentials.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes") // Sempre declarado no plural.
@Log4j2
// @AllArgsConstructor
// @RequiredArgsConstructor cria um construtor com todos os argumentos finals.
@RequiredArgsConstructor
public class AnimeController {

    //@Autowired -> Não precisa do autowired afinal, ele é injetado pelo construtor
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    //localhost:8080/anime/list
    //@RequestMapping(method = RequestMethod.GET, params = "list")  -> Tá deprecidado, o ideal é @GetMapping.
    @GetMapping // Não possui path, pois é o findAll
    public ResponseEntity<List<Anime>> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()) + "  " + getClass().getSimpleName());
        return ResponseEntity.ok(animeService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()) + "  " + getClass().getSimpleName());
        return ResponseEntity.ok(animeService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Anime> save(@RequestBody Anime anime) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()) + "  " + getClass().getSimpleName());
        return new ResponseEntity<>(animeService.save(anime), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()) + "  " + getClass().getSimpleName());
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> replace(@RequestBody Anime anime) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()) + "  " + getClass().getSimpleName());
        animeService.replace(anime);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

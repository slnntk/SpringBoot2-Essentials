package study.devdojo.springboot2essentials.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("anime")
@Log4j2
//@AllArgsConstructor
// @RequiredArgsConstructor cria um construtor com todos os argumentos finals.
@RequiredArgsConstructor
public class AnimeController {

    //@Autowired -> Não precisa do autowired afinal, ele é injetado pelo construtor
    private final DateUtil dateUtil;

    //localhost:8080/anime/list
    //@RequestMapping(method = RequestMethod.GET, params = "list")  -> Tá deprecidado, o ideal é @GetMapping.
    @GetMapping(path = "list")
    public List<Anime> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return List.of(new Anime("Hunter x Hunter"), new Anime("Beserk "));
    }
}

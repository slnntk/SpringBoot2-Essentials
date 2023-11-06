package study.devdojo.springboot2essentials.controller;

import org.springframework.web.bind.annotation.*;
import study.devdojo.springboot2essentials.domain.Anime;

import java.util.List;

@RestController
@RequestMapping("anime")
public class AnimeController {

    //localhost:8080/anime/list
    //@RequestMapping(method = RequestMethod.GET, params = "list")  -> Tá deprecidado, o ideal é @GetMapping.
    @GetMapping(path = "list")
    public List<Anime> list(){
        return List.of(new Anime("DBZ"), new Anime("Beserk"));
    }

}

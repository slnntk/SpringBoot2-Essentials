package study.devdojo.springboot2essentials.service;


// norma : rfc7231


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.repository.AnimeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
// @RequiredArgsConstructor
public class AnimeService{

    private static List<Anime> animes;
    // private final AnimeRepository animeRepository;

    static {
        animes =  new ArrayList<>(List.of(new Anime(1L, "Hunter x Hunter"), new Anime(2L, "Beserk ")));
    }

    public List<Anime> listAll() {
        return animes;
    }

    public Anime findById(Long id) {
        return animes.stream()
                .filter(anime -> anime.getId() == id)
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }

    public Anime save(Anime anime){
        anime.setId(ThreadLocalRandom.current().nextLong(3, 100000));
        animes.add(anime);
        return anime;
    }

    public void delete(Long id) {
        System.out.println("delete");
        animes.remove(findById(id));
    }

    public void replace(Anime anime){
        delete(anime.getId());
        animes.add(anime);
    }

}

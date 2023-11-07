package study.devdojo.springboot2essentials.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.repository.AnimeRepository;

import java.util.List;

@Service
// @RequiredArgsConstructor
public class AnimeService{

    private List<Anime> animes = List.of(new Anime(1L, "Hunter x Hunter"), new Anime(2L, "Beserk "));
    // private final AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animes;
    }

    public Anime findById(Long id) {
        return animes.stream()
                .filter(anime -> anime.getId() == id)
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }

}

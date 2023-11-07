package study.devdojo.springboot2essentials.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.repository.AnimeRepository;

import java.util.List;

@Service
// @RequiredArgsConstructor
public class AnimeService{

    // private final AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return List.of(new Anime(1L, "Hunter x Hunter"), new Anime(2L, "Beserk "));
    }

}

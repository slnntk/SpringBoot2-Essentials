package study.devdojo.springboot2essentials.service;


// norma : rfc7231


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.repository.AnimeRepository;
import study.devdojo.springboot2essentials.requests.AnimePostRequestBody;
import study.devdojo.springboot2essentials.requests.AnimePutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService{


    private final AnimeRepository animeRepository;


    public List<Anime> findAll() {
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(Long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not Found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody){
        Anime anime = Anime.builder()
                .name(animePostRequestBody.getName())
                .build();
        return animeRepository.save(anime);
    }

    public void delete(Long id) {
        animeRepository.deleteById(id);
    }

    public void replace(AnimePutRequestBody animePutRequestBody){
        findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = Anime.builder()
                .id(animePutRequestBody.getId())
                .name(animePutRequestBody.getName())
                .build();
        animeRepository.save(anime);
    }

}

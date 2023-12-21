package study.devdojo.springboot2essentials.service;


// norma : rfc7231


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.exception.ObjectNotFoundException;
import study.devdojo.springboot2essentials.mapper.AnimeMapper;
import study.devdojo.springboot2essentials.repository.AnimeRepository;
import study.devdojo.springboot2essentials.requests.AnimePostRequestBody;
import study.devdojo.springboot2essentials.requests.AnimePutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public List<Anime> listAllNonPageable() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Anime not Found"));
    }

    /**
     * Método para salvar um novo Anime no banco de dados.
     * Caso ocorra alguma exceção durante a execução do método, a transação será revertida (rollback),
     * desfazendo as operações realizadas até o momento. Isso mantém a consistência dos dados no banco.
     *
     * @param animePostRequestBody Corpo da requisição com os dados do Anime a ser criado.
     * @return Anime recém-criado e persistido no banco de dados.
     */
    @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
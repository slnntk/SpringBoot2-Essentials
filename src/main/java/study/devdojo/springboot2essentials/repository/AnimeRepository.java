package study.devdojo.springboot2essentials.repository;

import org.springframework.stereotype.Repository;
import study.devdojo.springboot2essentials.domain.Anime;

import java.util.List;

@Repository
public interface AnimeRepository {
    List<Anime> listAll();
}

package study.devdojo.springboot2essentials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.devdojo.springboot2essentials.domain.Anime;

import java.util.List;

@Repository // Spring Been de um repositorio.
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    List<Anime> findByName(String name);
}

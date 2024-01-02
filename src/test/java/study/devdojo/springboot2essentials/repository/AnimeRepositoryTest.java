package study.devdojo.springboot2essentials.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import study.devdojo.springboot2essentials.domain.Anime;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @DisplayName("Save creates anime when Successful")
    @Test
    void save_PersistAnime_WhenSuccessful() {
        Anime anime = createAnime();
        Anime animeSaved = this.animeRepository.save(anime);
        Assertions.assertThat(animeSaved)
                .isNotNull();
        Assertions.assertThat(animeSaved.getId())
                .isNotNull();
        Assertions.assertThat(animeSaved.getName())
                .isEqualTo(animeSaved.getName());
    }

    private Anime createAnime() {
        return Anime.builder()
                .name("Sousou no Frieren")
                .build();
    }
}
package study.devdojo.springboot2essentials.util;

import study.devdojo.springboot2essentials.domain.Anime;

public class AnimeCreator {

    /**
     * Método que cria um objeto Anime para ser salvo.
     *
     * @return Um objeto Anime a ser salvo.
     */
    public static Anime createAnimeToBeSaved() {
        return Anime.builder()
                .name("Sousou no Frieren")
                .build();
    }

    /**
     * Método que cria um objeto Anime válido.
     *
     * @return Um objeto Anime válido.
     */
    public static Anime createValidAnime() {
        return Anime.builder()
                .name("One Piece")
                .id(1L)
                .build();
    }


    /**
     * Método que cria um objeto Anime válido para atualização.
     *
     * @return Um objeto Anime válido para atualização.
     */
    public static Anime createValidUpdateAnime() {
        return Anime.builder()
                .name("HxH")
                .id(1L)
                .build();
    }
}

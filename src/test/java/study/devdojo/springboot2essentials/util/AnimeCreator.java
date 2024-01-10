package study.devdojo.springboot2essentials.util;

import study.devdojo.springboot2essentials.domain.Anime;

public class AnimeCreator {

    /**
     * Método que cria um objeto Anime para ser salvo.
     *
     * @return Um objeto Anime a ser salvo.
     */
    public static Anime createAnimeToBeSaved(){
        System.out.println("createAnimeToBeSaved");
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();
    }

    /**
     * Método que cria um objeto Anime válido.
     *
     * @return Um objeto Anime válido.
     */
    public static Anime createValidAnime(){
        System.out.println("createValidAnime");
        return Anime.builder()
                .name("Hajime no Ippo")
                .id(1L)
                .build();
    }


    /**
     * Método que cria um objeto Anime válido para atualização.
     *
     * @return Um objeto Anime válido para atualização.
     */
    public static Anime createValidUpdatedAnime(){
        System.out.println("createValidUpdatedAnime");
        return Anime.builder()
                .name("Hajime no Ippo 2")
                .id(1L)
                .build();
    }
}

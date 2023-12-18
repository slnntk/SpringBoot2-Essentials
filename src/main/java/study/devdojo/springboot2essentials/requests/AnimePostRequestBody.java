package study.devdojo.springboot2essentials.requests;

import lombok.Data;

/**
 * Representa o corpo da requisição para criação de um Anime.
 * Contém o nome do Anime a ser criado.
 */
@Data
public class AnimePostRequestBody {
    private String name; // Nome do Anime a ser criado na requisição.
}

package study.devdojo.springboot2essentials.requests;

import lombok.Data;

/**
 * Representa o corpo da requisição para atualização de um Anime.
 * Contém o ID e o novo nome do Anime a ser atualizado.
 */
@Data
public class AnimePutRequestBody {
    private Long id; // ID do Anime a ser atualizado na requisição.
    private String name; // Novo nome a ser atribuído ao Anime na atualização.
}

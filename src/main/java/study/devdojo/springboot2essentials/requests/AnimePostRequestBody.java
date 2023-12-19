package study.devdojo.springboot2essentials.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Representa o corpo da requisição para criação de um Anime.
 * Contém o nome do Anime a ser criado.
 * <p>
 * É anotada com @Data, uma anotação do Lombok que automaticamente gera métodos
 * getters, setters, toString, hashCode e equals baseados nos campos da classe.
 */
@Data
public class AnimePostRequestBody {

    /**
     * Representa o nome do Anime a ser criado na requisição.
     * É anotado com @NotEmpty, uma validação do Bean Validation que especifica que
     * este campo não pode ser vazio, garantindo que seja fornecido um valor não nulo
     * ou vazio para o nome do Anime. O atributo 'message' define a mensagem de erro
     * caso a validação falhe.
     */
    @NotEmpty(message = "The anime name cannot be null")
    private String name; // Nome do Anime a ser criado na requisição.
}

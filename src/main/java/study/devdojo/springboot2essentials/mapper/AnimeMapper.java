package study.devdojo.springboot2essentials.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import study.devdojo.springboot2essentials.domain.Anime;
import study.devdojo.springboot2essentials.requests.AnimePostRequestBody;
import study.devdojo.springboot2essentials.requests.AnimePutRequestBody;

/**
 * Classe responsável por mapear objetos relacionados a Animes, indica que a classe AnimeMapper é um
 * mapeador gerado pelo MapStruct para uso no Spring Framework.
 * Permitindo a injeção de dependências e o uso de outras funcionalidades do framework Spring no mapeador.
 */
@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    // Obtém uma instância do AnimeMapper para uso nos mapeamentos.
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    /**
     * Converte um AnimePostRequestBody em um objeto Anime.
     *
     * @param animePostRequestBody Objeto de requisição para criação de Anime.
     * @return Objeto Anime mapeado a partir do AnimePostRequestBody.
     */
    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

    /**
     * Converte um AnimePutRequestBody em um objeto Anime.
     *
     * @param animePutRequestBody Objeto de requisição para atualização de Anime.
     * @return Objeto Anime mapeado a partir do AnimePutRequestBody.
     */
    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);

}


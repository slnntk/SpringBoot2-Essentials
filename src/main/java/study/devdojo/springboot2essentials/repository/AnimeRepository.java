package study.devdojo.springboot2essentials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.devdojo.springboot2essentials.domain.Anime;

import java.util.List;

/**
 * Repositório responsável por fornecer métodos de acesso aos dados da entidade Anime.
 * Extende JpaRepository, que é uma interface fornecida pelo Spring Data JPA para operações CRUD.
 * Também define um método para buscar um Anime pelo nome.
 */
@Repository // Indica que essa interface é um Spring Bean de um repositório.
public interface AnimeRepository extends JpaRepository<Anime, Long> {

    /**
     * Método para buscar uma lista de Animes pelo nome.
     *
     * @param name Nome do Anime a ser buscado.
     * @return Lista de Animes com o nome especificado.
     */
    List<Anime> findByName(String name);
}

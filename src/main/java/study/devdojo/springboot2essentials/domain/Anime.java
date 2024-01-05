package study.devdojo.springboot2essentials.domain;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

/**
 * Representa a entidade Anime, identificada como uma entidade no contexto do banco de dados.
 */
@Entity // Identifica essa classe como uma entidade para mapeamento em um banco de dados.
@Data // Gera automaticamente getters, setters, equals e hashCode para os campos.
@Builder // Fornece um construtor para criar instâncias da classe Anime de forma mais concisa.
@AllArgsConstructor // Gera um construtor com todos os argumentos.
@NoArgsConstructor // Gera um construtor vazio.
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único do Anime no banco de dados.

    @NotEmpty(message = "The anime name cannot be empty")// Nome do Anime.
    private String name;
}

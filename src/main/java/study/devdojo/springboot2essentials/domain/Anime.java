package study.devdojo.springboot2essentials.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Identificar essa classe como uma entidade.
@Data // Gerar get, set, equals e hashcode]
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}

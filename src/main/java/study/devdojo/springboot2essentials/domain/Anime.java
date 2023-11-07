package study.devdojo.springboot2essentials.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Gerar get, set, equals e hashcode
@AllArgsConstructor
public class Anime {

    private Long id;
    private String name;
}

package br.alura.ONE_Challenge_LiterAlura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutorDTO(@JsonAlias("birth_year") String anoNascimento,
                            @JsonAlias("death_year") String anoMorte,
                            @JsonAlias("name") String nome
) {
}

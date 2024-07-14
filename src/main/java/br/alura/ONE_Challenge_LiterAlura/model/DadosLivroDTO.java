package br.alura.ONE_Challenge_LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivroDTO(@JsonAlias("id") Long id,
                            @JsonAlias("title") String titulo,
                            @JsonAlias("authors") ArrayList<DadosAutorDTO> dadosAutorDTO,
                            @JsonAlias("languages") ArrayList<String> idiomas,
                            @JsonAlias("download_count") Integer nDownloads
)
                          {
}

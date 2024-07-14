package br.alura.ONE_Challenge_LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSearchDTO(
        Integer count,
    ArrayList<DadosLivroDTO> results) {}


package br.alura.ONE_Challenge_LiterAlura.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}

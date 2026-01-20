package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Record que representa os dados de um episódio vindos da API OMDB
// @JsonIgnoreProperties(ignoreUnknown = true) ignora campos do JSON que não existem na classe
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String titulo,
                           @JsonAlias("Episode") Integer numero,
                           @JsonAlias("imdbRating") String avaliacao,
                           @JsonAlias("Released") String dataLancamento) {

    // Record automaticamente gera:
    // - Construtor com todos os parâmetros
    // - Métodos getter (titulo(), numero(), avaliacao(), dataLancamento())
    // - Métodos equals(), hashCode() e toString()
}
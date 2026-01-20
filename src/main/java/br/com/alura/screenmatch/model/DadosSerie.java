package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Record que representa os dados de uma série vindos da API OMDB
// @JsonAlias mapeia os nomes dos campos do JSON para os campos da classe Java
// @JsonIgnoreProperties(ignoreUnknown = true) ignora campos do JSON que não existem na classe
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo,
                        @JsonAlias("totalSeasons") Integer totalTemporadas,
                        @JsonAlias("imdbRating") String avaliacao) {

    // Record automaticamente gera:
    // - Construtor com todos os parâmetros
    // - Métodos getter (titulo(), totalTemporadas(), avaliacao())
    // - Métodos equals(), hashCode() e toString()
}
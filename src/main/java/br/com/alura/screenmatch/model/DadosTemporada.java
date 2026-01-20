package br.com.alura.screenmatch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Record que representa os dados de uma temporada vindos da API OMDB
// @JsonIgnoreProperties(ignoreUnknown = true) ignora campos do JSON que não existem na classe
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(
                             @JsonAlias("Season") Integer numero,
                             @JsonAlias("Episodes") List<DadosEpisodio> episodios
) {
    // Record automaticamente gera:
    // - Construtor com todos os parâmetros
    // - Métodos getter (numero(), episodios())
    // - Métodos equals(), hashCode() e toString()
}
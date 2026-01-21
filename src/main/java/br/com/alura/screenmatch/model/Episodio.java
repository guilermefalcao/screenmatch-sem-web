package br.com.alura.screenmatch.model;

import java.time.LocalDate;

public class Episodio {

    // Atributos privados - encapsulamento
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;


    //significa que o construtor é o mesmo nome da classe
    public Episodio(Integer numeroTemporada, DadosEpisodio dadosEpisodio){
        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodio.titulo();
        this.numeroEpisodio = dadosEpisodio.numero();
        
        // Tratamento para avaliação "N/A" - converte para Double ou define 0.0
        try {
            this.avaliacao = Double.valueOf(dadosEpisodio.avaliacao());
        } catch (NumberFormatException ex) {
            this.avaliacao = 0.0; // Se for "N/A", define como 0.0
        }
        
        // Tratamento para data de lançamento inválida
        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        } catch (Exception ex) {
            this.dataLancamento = null; // Se data inválida, define como null
        }
    }







    // GETTERS - métodos para ACESSAR (ler) os valores dos atributos privados
    
    public Integer getTemporada() {
        return temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    // SETTERS - métodos para MODIFICAR (escrever) os valores dos atributos privados
    
    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }




@Override
public String toString() {
    return "temporada=" + temporada +
            ", titulo='" + titulo + '\'' +
            ", numeroEpisodio=" + numeroEpisodio +
            ", avaliacao=" + avaliacao +
            ", dataLancamento=" + dataLancamento;
}






}

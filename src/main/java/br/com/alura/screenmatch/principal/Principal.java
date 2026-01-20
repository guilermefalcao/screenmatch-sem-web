package br.com.alura.screenmatch.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.DadosEpisodio;

// Classe principal que gerencia a interação com o usuário
public class Principal {

    // Scanner para ler entrada do usuário
    private Scanner leitura = new Scanner(System.in);

    // Instância da classe ConsumoAPI para fazer requisições HTTP
    private ConsumoAPI consumoApi = new ConsumoAPI();
    
    // Instância do conversor para transformar JSON em objetos Java
    private ConverteDados conversor = new ConverteDados();

    // Constantes para montar a URL da API OMDB
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    // Método para exibir um menu para o usuário
    public void exibirMenu() {
        System.out.println("Digite o nome da série para busca: ");
        var nomeSerie = leitura.nextLine();

        // Faz requisição para a API OMDB
        // Substitui espaços por "+" para formar URL válida
        var json = consumoApi.obterDados(
                ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        
        // Converte o JSON retornado em objeto DadosSerie
        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
        
        // Exibe os dados da série
        System.out.println(dadosSerie);
        
        // Lista para armazenar dados de todas as temporadas
        List<DadosTemporada> temporadas = new ArrayList<>();
        
        // Loop para buscar dados de cada temporada da série
        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            // Monta URL para buscar dados da temporada específica
            json = consumoApi.obterDados(
                ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            
            // Converte JSON da temporada em objeto DadosTemporada
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            
            // Adiciona a temporada na lista
            temporadas.add(dadosTemporada);
        }
        
        // Exibe informações de todas as temporadas
        temporadas.forEach(System.out::println);

        // Loop aninhado comentado - versão com for tradicional
        /*
        for(int i = 0; i < dadosSerie.totalTemporadas(); i++){
            // Obtém a lista de episódios da temporada atual
            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
            
            // Percorre cada episódio da temporada atual
            for(int j = 0; j < episodiosTemporada.size(); j++){
                // Exibe apenas o título do episódio
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }
        */
        
        // Versão com forEach - mais funcional e legível
        // Para cada temporada na lista de temporadas
        temporadas.forEach(temporada -> {
            // Para cada episódio na lista de episódios da temporada atual
            temporada.episodios().forEach(episodio -> {       //usando lambdas 
                // Exibe apenas o título do episódio
                System.out.println(episodio.titulo());
            });
        });


    }
}

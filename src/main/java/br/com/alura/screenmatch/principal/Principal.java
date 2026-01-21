package br.com.alura.screenmatch.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
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

        // Validação de entrada vazia
        if (nomeSerie == null || nomeSerie.trim().isEmpty()) {
            System.out.println("Nome da série não pode estar vazio!");
            return;
        }

        // Faz requisição para a API OMDB
        // Substitui espaços por "+" para formar URL válida
        var json = consumoApi.obterDados(
                ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        
        // Converte o JSON retornado em objeto DadosSerie
        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
        
        // Validação se a série foi encontrada
        if (dadosSerie.titulo() == null || dadosSerie.totalTemporadas() == null) {
            System.out.println("Série não encontrada! Verifique o nome e tente novamente.");
            return;
        }
        
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


        //todos os episodios de todas as temporadas estejam em uma lista so... e nao uma lista dentro de lista;
        //utilizar stream;
        // para encontrar o top 5


        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(temp -> temp.episodios().stream())  //gera fluxo de dados com todas as temporadas
                .collect(Collectors.toList());  //pega o resultado e joga em uma nova lista

                System.out.println("\nTop 10 episódios:");
                dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A "))
                .peek(e -> System.out.println("---- Primeiro filtro (N/A) " + e )) // peek() permite "espiar" os dados em cada etapa do pipeline sem modificá-los
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .peek(e -> System.out.println("---- Ordenação " + e )) // Mostra como os dados estão após a ordenação
                .limit(10)
                .peek(e -> System.out.println("---- Limite " +e )) // Exibe apenas os 10 primeiros após aplicar limit()
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("---- Mapeamento " +e )) // Mostra os títulos já convertidos para maiúsculas
                .forEach(System.out::println);

        // Convertendo DadosEpisodio para objetos Episodio usando streams
        // flatMap() "achata" as listas de episódios de cada temporada em uma única lista
        List<Episodio> episodios = temporadas.stream()
                .flatMap(temp -> temp.episodios().stream()
                        .map(dadosEpisodio -> new Episodio(temp.numero(), dadosEpisodio))
                )
                .collect(Collectors.toList());

        // Agrupamento de dados - Map com estrutura chave:valor (temporada:média de avaliação)
        System.out.println("\n=== AVALIAÇÕES MÉDIAS POR TEMPORADA ===");
        
        // Filtra episódios com avaliação válida (> 0.0) e agrupa por temporada
        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0) // Remove episódios com avaliação 0.0 (eram "N/A")
                .collect(Collectors.groupingBy(
                        Episodio::getTemporada, // Chave: número da temporada
                        Collectors.averagingDouble(Episodio::getAvaliacao) // Valor: média das avaliações
                ));
        
        // Exibe o resultado formatado
        System.out.println(avaliacoesPorTemporada);
        
        // Exibição mais legível das avaliações por temporada
        avaliacoesPorTemporada.forEach((temporada, media) -> 
            System.out.println("Temporada " + temporada + ": " + String.format("%.2f", media))
        );

        // Exibe todos os episódios usando o toString() personalizado
        episodios.forEach(System.out::println);



        // Busca por trecho do título - findFirst() retorna Optional<Episodio>
        System.out.println("\nDigite um trecho do titulo do episodio que deseja encontrar:");
        var trechoTitulo = leitura.nextLine();

        // Optional é usado para evitar NullPointerException quando nenhum elemento é encontrado
        Optional<Episodio> episodioEncontrado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase())) // Busca case-insensitive
                .findFirst(); // Retorna Optional<Episodio> - pode estar presente ou vazio

        // Verifica se encontrou algum episódio
        if (episodioEncontrado.isPresent()) {
            System.out.println("Episódio encontrado: " + episodioEncontrado.get());
        } else {
            System.out.println("Nenhum episódio encontrado com o trecho: " + trechoTitulo);
        }




        // Filtro por ano - pergunta ao usuário a partir de que ano deseja ver os episódios
        System.out.println("\nA partir de que ano você deseja ver os episódios?");
        var ano = leitura.nextInt(); // Lê o ano digitado pelo usuário
        leitura.nextLine(); // Limpa o buffer do scanner

        // Cria data de busca - 1º de janeiro do ano informado
        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        // Formatador para exibir data no formato brasileiro (dd/MM/yyyy)
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Filtra episódios lançados após a data de busca e exibe formatado
        System.out.println("\nEpisódios lançados a partir de " + ano + ":");
        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                        " - Episódio: " + e.getTitulo() +
                        " - Data lançamento: " + e.getDataLancamento().format(formatador)
                ));
        
        System.out.println("\n=== FIM DA CONSULTA ===");
                


        // Estatísticas completas das avaliações - DoubleSummaryStatistics
        System.out.println("\n=== ESTATÍSTICAS DAS AVALIAÇÕES ===");
        
        // DoubleSummaryStatistics coleta várias estatísticas em uma única operação
        DoubleSummaryStatistics estatisticas = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0) // Remove episódios com avaliação 0.0 (eram "N/A")
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

        // Exibição formatada das estatísticas
        System.out.println("Quantidade de episódios avaliados: " + estatisticas.getCount());
        System.out.println("Menor avaliação: " + String.format("%.1f", estatisticas.getMin()));
        System.out.println("Maior avaliação: " + String.format("%.1f", estatisticas.getMax()));
        System.out.println("Média das avaliações: " + String.format("%.2f", estatisticas.getAverage()));
        System.out.println("Soma total das avaliações: " + String.format("%.1f", estatisticas.getSum()));

        System.out.println("\n=== ============ ===");




        /* exercicios sobre lista stream: 

        // Encadeando operações com streams - exemplo de programação funcional
        List<String> nomes = Arrays.asList("Java", "JavaScript", "Python", "C++", "JJJJ1",  "JJJJ2", "JJJJ3", "JJJJ4", "JJJJ5", "JJJJ6", "JJJJ7", "JJJJ8", "JJJJ9");
        nomes.stream()
                .filter(n -> n.startsWith("J"))  // Filtra nomes que começam com "J"
                .sorted()                         // Ordena alfabeticamente
                .limit(5)                         // Limita a 5 nomes
                .forEach(System.out::println);   // Exibe cada nome na tela

        // Exemplo 1: Filtrar números pares e coletar em lista
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> numerosPares = numeros.stream()
                                           .filter(n -> n % 2 == 0)
                                           .collect(Collectors.toList());
        System.out.println(numerosPares); // Output: [2, 4, 6, 8, 10]

        // Exemplo 2: Mapear strings para seus tamanhos
        List<String> palavras = Arrays.asList("Java", "Stream", "Operações", "Intermediárias");
        List<Integer> tamanhos = palavras.stream()
                                        .map(s -> s.length())
                                        .collect(Collectors.toList());
        System.out.println(tamanhos); // Output: [4, 6, 11, 14]

        // Exemplo 3: ForEach com saudação personalizada
        List<String> nomesUsuarios = Arrays.asList("João", "Maria", "Pedro", "Ana");
        nomesUsuarios.stream()
                     .forEach(nome -> System.out.println("Olá, " + nome + "!"));

        // Exemplo 4: Filtrar números pares e coletar em Set
        List<Integer> numerosSet = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Set<Integer> numerosParesSet = numerosSet.stream()
                                                 .filter(n -> n % 2 == 0)
                                                 .collect(Collectors.toSet());
        System.out.println(numerosParesSet); // Output: [2, 4, 6, 8, 10]

*/

    }
}

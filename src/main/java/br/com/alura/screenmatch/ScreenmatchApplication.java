package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

// Anotação que marca esta classe como uma aplicação Spring Boot
// Combina @Configuration, @EnableAutoConfiguration e @ComponentScan
@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	// Método principal que inicia a aplicação Spring Boot
	public static void main(String[] args) {
		// Executa a aplicação Spring Boot passando a classe principal e argumentos
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	// Método executado automaticamente após a inicialização do Spring
	// CommandLineRunner permite executar código quando a aplicação inicia
	@Override
	public void run(String... args) throws Exception {
		// Cria uma instância da classe ConsumoAPI para fazer requisições HTTP
		var consumoApi = new ConsumoAPI();
		
		// Faz requisição para a API OMDB buscando dados da série Gilmore Girls
		var json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		
		// Cria instância do conversor de dados
		ConverteDados conversor = new ConverteDados();
		
		// Converte o JSON da série em objeto DadosSerie
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		
		// Exibe apenas o objeto DadosSerie convertido (campos mapeados)
		System.out.println(dados);
		
		// Faz requisição à API do café (apenas para demonstração)
		json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
	}
}
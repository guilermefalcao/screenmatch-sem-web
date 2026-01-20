package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.exercicios.ExerciciosLambda;
import br.com.alura.screenmatch.principal.Principal;

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
		// Cria instância da classe Principal que contém a lógica do menu
		Principal principal = new Principal();

		
		
		// Chama o método que exibe o menu para interação com o usuário
		principal.exibirMenu();


		//para chamar os exercicios da aula de lambda expressions
		//ExerciciosLambda.executarTodos();
		
		// Código comentado - exemplo de busca de temporadas (movido para Principal)
		/*
		List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i = 1; i <= dados.totalTemporadas(); i++) {
			json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=6585022c");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);		
		}
		temporadas.forEach(System.out::println);
		*/
	}
}
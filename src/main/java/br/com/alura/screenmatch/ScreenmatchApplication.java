package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		// Executa apenas o projeto de séries
		Principal principal = new Principal();
		principal.exibirMenu();
	}
}
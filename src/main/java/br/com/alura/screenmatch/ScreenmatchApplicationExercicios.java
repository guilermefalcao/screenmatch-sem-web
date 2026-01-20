package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.exercicios.ExerciciosLambda;
import br.com.alura.screenmatch.principal.Principal;

/**
 * Classe alternativa para testar exercícios de Lambda
 * 
 * COMO USAR:
 * 1. Renomeie ScreenmatchApplication.java para ScreenmatchApplicationOriginal.java
 * 2. Renomeie este arquivo para ScreenmatchApplication.java
 * 3. Execute: mvn spring-boot:run
 * 4. Para voltar ao projeto original, desfaça as alterações
 */
@SpringBootApplication
public class ScreenmatchApplicationExercicios implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplicationExercicios.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Executar exercícios de Lambda");
        System.out.println("2 - Executar projeto de séries");
        System.out.println();
        
        // Para testar os exercícios, descomente a linha abaixo:
        ExerciciosLambda.executarTodos();
        
        // Para testar o projeto de séries, descomente as linhas abaixo:
        // Principal principal = new Principal();
        // principal.exibirMenu();
    }
}